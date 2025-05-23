package com.utadeo.miguelabarreram.simuladortransaccional.database;

import java.sql.Types;

import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;

public class SQLiteDialect extends Dialect {
	
	public SQLiteDialect() {
		super(DatabaseVersion.make(3, 41));
	}
	
	@Override
	protected String columnType(int sqlTypeCode) {
		switch(sqlTypeCode) {
			case Types.BIT:
				return "boolean";
			case Types.BINARY:
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
				return "blob";
			default:
				return super.columnType(sqlTypeCode);
		}
	}
	
}
