CREATE TABLE role (
    role_id   INTEGER      PRIMARY KEY
                           UNIQUE
                           NOT NULL,
    role_name VARCHAR (50) UNIQUE
                           NOT NULL
);

INSERT INTO role VALUES (1, 'Administrator'), (2, 'Editor'), (3, 'Tester');

CREATE TABLE user (
    user_id            INTEGER       PRIMARY KEY
                                     UNIQUE
                                     NOT NULL,
    user_name          VARCHAR (250) UNIQUE
                                     NOT NULL,
    email              VARCHAR (250) UNIQUE
                                     NOT NULL,
    password           VARCHAR (250) NOT NULL,
    role_id            INTEGER       REFERENCES role (role_id)
                                     NOT NULL,
    enabled            BOOLEAN       NOT NULL
                                     DEFAULT (TRUE),
    last_updated       DATETIME      NOT NULL,
    last_login         DATETIME,
    last_failed_login  DATETIME,
    count_failed_login INTEGER       DEFAULT (0),
    updated_by         INTEGER       REFERENCES user (user_id)
                                     NULL
);

CREATE TABLE iso_template(
    iso_template_id          INTEGER       PRIMARY KEY 
                                           UNIQUE 
                                           NOT NULL,
    iso_template_name        VARCHAR (50)  NOT NULL,
    base_on                  INTEGER       REFERENCES iso_template (iso_template_id)
                                           NULL, 
    iso_template_description VARCHAR (250) NULL,
    last_updated             DATETIME      NOT NULL,
    type_visibility          VARCHAR (1)   DEFAULT ('U'),
    user_id                  INTEGER       REFERENCES user (user_id)
                                           NOT NULL,
    max_valid_field          INTEGER       NULL,
    bitmap_field             INTEGER       NULL,
    first_field              INTEGER       NULL,
    emit_bitmap              BOOLEAN       NULL,
    header_length            INTEGER       NULL,
    UNIQUE(iso_template_name, base_on, user_id)
);

INSERT INTO iso_template VALUES ('1', 'Iso8583', NULL, 'Base Template, from Standar Iso 8583', '1747590380500', 'A', '1', '128', '1', '-1', '1', '-1');

CREATE TABLE iso_field(
    iso_field_id           INTEGER      PRIMARY KEY
                                        UNIQUE
                                        NOT NULL,
    iso_field_nr           INTEGER      NOT NULL,
    iso_field_name         VARCHAR(70)  NOT NULL,
    iso_field_class        VARCHAR(150) NOT NULL,
    iso_field_length       INTEGER      NOT NULL,
    iso_field_pad          BOOLEAN      DEFAULT(FALSE)
                                        NOT NULL,
    iso_field_type_pad     VARCHAR(1)   DEFAULT('')
                                        NOT NULL,
    iso_has_sub_fields     BOOLEAN      DEFAULT(FALSE)
                                        NOT NULL,
    iso_emit_bitmap        BOOLEAN      DEFAULT(FALSE)
                                        NULL,
    iso_max_valid_field    INTEGER      DEFAULT(-1)
                                        NULL,
    iso_bitmap_field       INTEGER      DEFAULT(-1)
                                        NULL,
    iso_first_field        INTEGER      DEFAULT(-1)
                                        NULL,
    iso_header_length      INTEGER      DEFAULT(-1)
                                        NULL,
    iso_parent_field       INTEGER      REFERENCES iso_field (iso_field_id)
                                        NULL,
    iso_template_id        INTEGER      REFERENCES iso_template (iso_template_id)
                                        NOT NULL,
    UNIQUE(iso_field_nr, iso_template_id, iso_parent_field)
);

INSERT INTO iso_field VALUES ('1', '1', 'Bit Map', 'org.jpos.iso.IFA_BITMAP', '64', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('2', '2', 'Primary account number (PAN)', 'org.jpos.iso.IFA_LLNUM', '19', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('3', '3', 'Processing code', 'org.jpos.iso.IFA_NUMERIC', '6', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('4', '4', 'Amount, transaction', 'org.jpos.iso.IFA_AMOUNT', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '1', 'L');
INSERT INTO iso_field VALUES ('5', '5', 'Amount, Settlement', 'org.jpos.iso.IFA_AMOUNT', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('6', '6', 'Amount, cardholder billing', 'org.jpos.iso.IFA_AMOUNT', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('7', '7', 'Transmission date & time', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('8', '8', 'Amount, Cardholder billing fee', 'org.jpos.iso.IFA_AMOUNT', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('9', '9', 'Conversion rate, Settlement', 'org.jpos.iso.IFA_NUMERIC', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('10', '10', 'Conversion rate, cardholder billing', 'org.jpos.iso.IFA_NUMERIC', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('11', '11', 'Systems trace audit number', 'org.jpos.iso.IFA_NUMERIC', '6', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('12', '12', 'Time, Local transaction', 'org.jpos.iso.IFA_NUMERIC', '6', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('13', '13', 'Date, Local transaction (MMdd)', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('14', '14', 'Date, Expiration', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('15', '15', 'Date, Settlement', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('16', '16', 'Date, conversion', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('17', '17', 'Date, capture', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('18', '18', 'Merchant type', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('19', '19', 'Acquiring institution country code', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('20', '20', 'PAN Extended, country code', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('21', '21', 'Forwarding institution. country code', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('22', '22', 'Point of service entry mode', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('23', '23', 'Application PAN number', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('24', '24', 'Function code(ISO 8583:1993)/Network International identifier (?)', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('25', '25', 'Point of service condition code', 'org.jpos.iso.IFA_NUMERIC', '2', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('26', '26', 'Point of service capture code', 'org.jpos.iso.IFA_NUMERIC', '2', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('27', '27', 'Authorizing identification response length', 'org.jpos.iso.IFA_NUMERIC', '1', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('28', '28', 'Amount, transaction fee', 'org.jpos.iso.IFA_NUMERIC', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('29', '29', 'Amount. settlement fee', 'org.jpos.iso.IFA_NUMERIC', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('30', '30', 'Amount, transaction processing fee', 'org.jpos.iso.IFA_NUMERIC', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('31', '31', 'Amount, settlement processing fee', 'org.jpos.iso.IFA_NUMERIC', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('32', '32', 'Acquiring institution identification code', 'org.jpos.iso.IFA_LLNUM', '11', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('33', '33', 'Forwarding institution identification code', 'org.jpos.iso.IFA_LLNUM', '11', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('34', '34', 'Primary account number, extended', 'org.jpos.iso.IFA_LLCHAR', '28', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('35', '35', 'Track 2 data', 'org.jpos.iso.IFA_LLNUM', '37', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('36', '36', 'Track 3 data', 'org.jpos.iso.IFA_LLLCHAR', '104', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('37', '37', 'Retrieval reference number', 'org.jpos.iso.IF_CHAR', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('38', '38', 'Authorization identification response', 'org.jpos.iso.IF_CHAR', '6', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('39', '39', 'Response code', 'org.jpos.iso.IF_CHAR', '2', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('40', '40', 'Service restriction code', 'org.jpos.iso.IF_CHAR', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('41', '41', 'Card acceptor terminal identification', 'org.jpos.iso.IF_CHAR', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('42', '42', 'Card acceptor identification code', 'org.jpos.iso.IF_CHAR', '15', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('43', '43', 'Card acceptor name/location', 'org.jpos.iso.IF_CHAR', '40', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('44', '44', 'Additional response data', 'org.jpos.iso.IFA_LLCHAR', '25', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('45', '45', 'Track 1 Data', 'org.jpos.iso.IFA_LLCHAR', '76', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('46', '46', 'Additional data - ISO', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('47', '47', 'Additional data - National', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('48', '48', 'Additional data - Private', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('49', '49', 'Currency code, transaction', 'org.jpos.iso.IF_CHAR', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('50', '50', 'Currency code, settlement', 'org.jpos.iso.IF_CHAR', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('51', '51', 'Currency code, cardholder billing', 'org.jpos.iso.IF_CHAR', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('52', '52', 'Personal Identification number data', 'org.jpos.iso.IFA_NUMERIC', '16', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('53', '53', 'Security related control information', 'org.jpos.iso.IFA_NUMERIC', '18', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('54', '54', 'Additional amounts', 'org.jpos.iso.IFA_LLLCHAR', '120', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('55', '55', 'Reserved ISO', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('56', '56', 'Reserved ISO', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('57', '57', 'Reserved National', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('58', '58', 'Reserved National', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('59', '59', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('60', '60', 'Advice/reason code (private reserved)', 'org.jpos.iso.IFA_LLLCHAR', '7', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('61', '61', 'Reserved Private', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('62', '62', 'Reserved Private', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('63', '63', 'Reserved Private', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('64', '64', 'Message authentication code (MAC)', 'org.jpos.iso.IFA_BINARY', '16', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('65', '65', 'Bit map, tertiary', 'org.jpos.iso.IFA_BINARY', '16', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('66', '66', 'Settlement code', 'org.jpos.iso.IFA_NUMERIC', '1', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('67', '67', 'Extended payment code', 'org.jpos.iso.IFA_NUMERIC', '2', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('68', '68', 'Receiving institution country code', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('69', '69', 'Settlement institution county code', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('70', '70', 'Network management Information code', 'org.jpos.iso.IFA_NUMERIC', '3', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('71', '71', 'Message number', 'org.jpos.iso.IFA_NUMERIC', '4', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('72', '72', 'Data record (ISO 8583:1993)/n 4 Message number, last(?)', 'org.jpos.iso.IFA_NUMERIC', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('73', '73', 'Date, Action', 'org.jpos.iso.IFA_NUMERIC', '6', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('74', '74', 'Credits, number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('75', '75', 'Credits, reversal number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('76', '76', 'Debits, number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('77', '77', 'Debits, reversal number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('78', '78', 'Transfer number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('79', '79', 'Transfer, reversal number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('80', '80', 'Inquiries number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('81', '81', 'Authorizations, number', 'org.jpos.iso.IFA_NUMERIC', '10', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('82', '82', 'Credits, processing fee amount', 'org.jpos.iso.IFA_NUMERIC', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('83', '83', 'Credits, transaction fee amount', 'org.jpos.iso.IFA_NUMERIC', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('84', '84', 'Debits, processing fee amount', 'org.jpos.iso.IFA_NUMERIC', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('85', '85', 'Debits, transaction fee amount', 'org.jpos.iso.IFA_NUMERIC', '12', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('86', '86', 'Credits, amount', 'org.jpos.iso.IFA_NUMERIC', '15', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('87', '87', 'Credits, reversal amount', 'org.jpos.iso.IFA_NUMERIC', '15', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('88', '88', 'Debits, amount', 'org.jpos.iso.IFA_NUMERIC', '15', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('89', '89', 'Debits, reversal amount', 'org.jpos.iso.IFA_NUMERIC', '15', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('90', '90', 'Original data elements', 'org.jpos.iso.IFA_NUMERIC', '42', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('91', '91', 'File update code', 'org.jpos.iso.IF_CHAR', '1', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('92', '92', 'File security code', 'org.jpos.iso.IF_CHAR', '2', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('93', '93', 'Response indicator', 'org.jpos.iso.IF_CHAR', '5', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('94', '94', 'Service indicator', 'org.jpos.iso.IF_CHAR', '7', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('95', '95', 'Replacement amounts', 'org.jpos.iso.IF_CHAR', '42', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('96', '96', 'Message security code', 'org.jpos.iso.IFA_BINARY', '8', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('97', '97', 'Amount, net settlement', 'org.jpos.iso.IFA_AMOUNT', '16', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('98', '98', 'Payee', 'org.jpos.iso.IF_CHAR', '25', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('99', '99', 'Settlement institution identification code', 'org.jpos.iso.IFA_LLNUM', '11', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('100', '100', 'Receiving institution identification code', 'org.jpos.iso.IFA_LLNUM', '11', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('101', '101', 'File name', 'org.jpos.iso.IFA_LLCHAR', '17', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('102', '102', 'Account identification 1', 'org.jpos.iso.IFA_LLCHAR', '28', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('103', '103', 'Account identification 2', 'org.jpos.iso.IFA_LLCHAR', '28', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('104', '104', 'Transaction description', 'org.jpos.iso.IFA_LLLCHAR', '100', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('105', '105', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('106', '106', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('107', '107', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('108', '108', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('109', '109', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('110', '110', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('111', '111', 'Reserved for ISO use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('112', '112', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('113', '113', 'Authorizing agent institution id code', 'org.jpos.iso.IFA_LLLCHAR', '11', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('114', '114', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('115', '115', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('116', '116', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('117', '117', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('118', '118', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('119', '119', 'Reserved for national use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('120', '120', 'Reserved for private use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('121', '121', 'Reserved for private use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('122', '122', 'Reserved for private use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('123', '123', 'Reserved for private use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('124', '124', 'Info Text', 'org.jpos.iso.IFA_LLLCHAR', '255', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('125', '125', 'Network management information', 'org.jpos.iso.IFA_LLLCHAR', '50', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('126', '126', 'Issuer trace id', 'org.jpos.iso.IFA_LLLCHAR', '6', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('127', '127', 'Reserved for private use', 'org.jpos.iso.IFA_LLLCHAR', '999', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');
INSERT INTO iso_field VALUES ('128', '128', 'Message Authentication code', 'org.jpos.iso.IFA_BINARY', '16', '0', '0', '0', '0', '0', '0', NULL, '1', '0', '');

CREATE TABLE iso_channel (
    iso_channel_id          INTEGER      PRIMARY KEY
                                         UNIQUE
                                         NOT NULL,
    iso_channel_name        VARCHAR(10)  NOT NULL,
    iso_channel_type        VARCHAR(1)   NOT NULL,
    iso_channel_packager    VARCHAR(100) NOT NULL,
    iso_channel_owner       INTEGER      REFERENCES user (user_id)
                                         NOT NULL,
    iso_channel_visibility  VARCHAR(1)   DEFAULT ('U'),
    iso_channel_description VARCHAR(1000),
    last_updated            DATETIME,
    updated_by              INTEGER      REFERENCES user (user_id)
                                         NOT NULL,
    UNIQUE(iso_channel_name, iso_channel_owner)
);

INSERT INTO iso_channel VALUES (1, 'CSChannel', 'J', 'org.jpos.iso.channel.CSChannel', 1, 'A', 'LL LL 00 00 [header] ISO-DATA LL LL represents the [header+] ISO-DATA length in network byte order 00 00 reserved for future use The header is optional ISO-DATA: ISO-8583 image', 1707447396890, 1);
INSERT INTO iso_channel VALUES (2, 'NACChannel', 'J', 'org.jpos.iso.channel.NACChannel', 1, 'A', 'LL LL [TPDU] ISO-DATA LL LL represents the TPDU+ISO-DATA length in network byte order Optional TPDU (transport protocol data unit) ISO-DATA: ISO-8583 image', 1707447396890, 1);
INSERT INTO iso_channel VALUES (3, 'NCCChannel', 'J', 'org.jpos.iso.channel.NCCChannel', 1, 'A', 'LL LL [TPDU] ISO-DATA LL LL represents the TPDU+ISO-DATA length in BCD (binary coded decimal) Optional TPDU (transport protocol data unit) ISODATA: ISO-8583 image', 1707447396890, 1);
INSERT INTO iso_channel VALUES (4, 'ASCIIChannel', 'J', 'org.jpos.iso.channel.ASCIIChannel', 1, 'A', 'LLLL [header] ISO-DATA LLLL four bytes ASCII [header+] ISO-DATA length Optional header ISO-DATA: ISO-8583 image', 1707447396890, 1);
INSERT INTO iso_channel VALUES (5, 'RawChannel', 'J', 'org.jpos.iso.channel.RawChannel', 1, 'A', 'LL LL LL LL [header] ISO-DATA LL LL LL LL is [header+] ISO-DATA length in network byte order ISO-DATA: ISO-8583 image', 1707447396890, 1);
INSERT INTO iso_channel VALUES (6, 'VAPChannel', 'J', 'org.jpos.iso.channel.VAPChannel', 1, 'A', 'LL LL 00 00 header ISO-DATA LL LL represents the header+ISO-DATA length in network byte order 00 00 reserved for future use VAP-specific header ISODATA: ISO-8583 image', 1707447396890, 1);
INSERT INTO iso_channel VALUES (7, 'PADChannel', 'J', 'org.jpos.iso.channel.PADChannel', 1, 'A', '[header] ISO-DATA Stream-based channel reads messages on-the-fly without using any kind of message boundary indicator.', 1707447396890, 1);
INSERT INTO iso_channel VALUES (8, 'X25Channel', 'J', 'org.jpos.iso.channel.X25Channel', 1, 'A', 'X25 is similar to PADChannel but uses a slightly different strategy. Instead of pulling an ISO-8583 from a stream, unpacking it on the fly, X25Channel attempts to read full TCP/IP packets by specifying a small timeout value. Whenever possible, PADChannel seems like a better solution; however, certain X.25 packet assembler/disassemblers sometimes send garbage over the wire (i.e. ETXs) which might confuse PADChannel.', 1707447396890, 1);
INSERT INTO iso_channel VALUES (9, 'XMLChannel', 'J', 'org.jpos.iso.channel.XMLChannel', 1, 'A', 'Send/Receive messages in jPOS’s internal XML message representation', 1707447396890, 1);
INSERT INTO iso_channel VALUES (10, 'LogChannel', 'J', 'org.jpos.iso.channel.LogChannel', 1, 'A', 'Similar to XMLChannel, but you can feed it a jPOS Log, which is suitable to replay sessions', 1707447396890, 1);

CREATE TABLE iso_node (
    iso_node_id              INTEGER      PRIMARY KEY
                                          UNIQUE
                                          NOT NULL,
    iso_node_name            VARCHAR(100) NOT NULL,
    iso_node_type            VARCHAR(1)   NOT NULL,
    iso_node_host            VARCHAR(50),
    iso_node_port            INTEGER      NOT NULL,
    iso_channel_id           INTEGER      REFERENCES iso_channel (iso_channel_id)
                                          NOT NULL,
    iso_template_id          INTEGER      REFERENCES iso_template (iso_template_id)
                                          NOT NULL,
    iso_node_is_started      BOOLEAN      DEFAULT(false),
    iso_node_owner           INTEGER      REFERENCES user (user_id)
                                          NOT NULL,
    iso_node_visibility      VARCHAR(1)   DEFAULT ('U'),
    iso_node_description     VARCHAR(100),
    iso_node_time_out        LONG         DEFAULT(3600),
    iso_node_reconnect_delay LONG,
    last_updated             DATETIME,
    updated_by               INTEGER      REFERENCES user (user_id)
                                          NOT NULL,
    UNIQUE(iso_node_name, iso_node_owner)
);

CREATE TABLE iso_msg_tpl (
    iso_msg_tpl_id    INTEGER       PRIMARY KEY
                                    UNIQUE
                                    NOT NULL,
    iso_msg_tpl_name  VARCHAR(100)  NOT NULL,
    iso_msg_type      VARCHAR(4)    NOT NULL,
    iso_template_id   INTEGER       REFERENCES iso_template (iso_template_id)
                                    NOT NULL,
	iso_msg_desc      VARCHAR(1000) NOT NULL,
    iso_msg_vsby      VARCHAR(1)    DEFAULT ('U'),
    iso_msg_tpl_owner INTEGER       REFERENCES user (user_id)
                                    NOT NULL,
    last_updated      DATETIME,     
    updated_by        INTEGER       REFERENCES user (user_id)
                                    NOT NULL,
    UNIQUE(iso_template_id, iso_msg_tpl_name, iso_msg_tpl_owner)
);

CREATE TABLE iso_msg_flds_tpl (
    iso_msg_flds_tpl_id  INTEGER       PRIMARY KEY
                                       UNIQUE
                                       NOT NULL,
    iso_msg_tpl_id       INTEGER       REFERENCES iso_msg_tpl (iso_msg_tpl_id)
                                       NOT NULL,
    iso_field_id         INTEGER       REFERENCES iso_field (iso_field_id)
                                       NOT NULL,
    is_val_auto          BOOLEAN       DEFAULT(false),
    is_val_fixed         BOOLEAN       DEFAULT(false),
    iso_msg_flds_tpl_val VARCHAR(100),
    UNIQUE(iso_msg_tpl_id, iso_field_id)
);