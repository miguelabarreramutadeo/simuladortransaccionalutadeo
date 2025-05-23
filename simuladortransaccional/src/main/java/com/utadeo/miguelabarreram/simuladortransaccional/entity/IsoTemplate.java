package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.Tools;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class IsoTemplate implements Serializable {
	
	@Transient
	@Autowired
	Tools tools;

	private static final long serialVersionUID = 8541949446530449437L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isoTemplateId;
	private String isoTemplateName;
	
	@ManyToOne()
	@JoinColumn(name = "baseOn")
	private IsoTemplate baseOn;
	
	@JsonIgnore
	@OneToMany(mappedBy = "baseOn")
	private Collection<IsoTemplate> isoTemplateChildren;	
	
	private String isoTemplateDescription;
	private Timestamp lastUpdated;
	private String typeVisibility;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId")
	private User userId;
	
	private int maxValidField;
	private int bitmapField;
	private int firstField;
	private boolean emitBitmap;
	private int headerLength;
	
	@JsonIgnore
	@OneToMany(targetEntity = IsoField.class, mappedBy = "isoTemplateId")
	private Collection<IsoField> isoFields;
	
	public IsoTemplate() {
	}

	@JsonCreator
	public IsoTemplate(int isoTemplateId, String isoTemplateName, IsoTemplate baseOn, String isoTemplateDescription,
			String typeVisibility, int maxValidField, int bitmapField,
			int firstField, boolean emitBitmap, int headerLength, Timestamp lastUpdated) {
		this.isoTemplateId = isoTemplateId;
		this.isoTemplateName = isoTemplateName;
		this.baseOn = baseOn;
		this.isoTemplateDescription = isoTemplateDescription;
		this.typeVisibility = typeVisibility;
		this.maxValidField = maxValidField;
		this.bitmapField = bitmapField;
		this.firstField = firstField;
		this.emitBitmap = emitBitmap;
		this.headerLength = headerLength;
		this.lastUpdated = lastUpdated;
	}

	public int getIsoTemplateId() {
		return isoTemplateId;
	}

	public void setIsoTemplateId(int isoTemplateId) {
		this.isoTemplateId = isoTemplateId;
	}

	public String getIsoTemplateName() {
		return isoTemplateName;
	}

	public void setIsoTemplateName(String isoTemplateName) {
		this.isoTemplateName = isoTemplateName;
	}

	public IsoTemplate getBaseOn() {
		return baseOn;
	}

	public void setBaseOn(IsoTemplate baseOn) {
		this.baseOn = baseOn;
	}

	public String getIsoTemplateDescription() {
		return isoTemplateDescription;
	}

	public void setIsoTemplateDescription(String isoTemplateDescription) {
		this.isoTemplateDescription = isoTemplateDescription;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getTypeVisibility() {
		return typeVisibility;
	}

	public void setTypeVisibility(String typeVisibility) {
		this.typeVisibility = typeVisibility;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	public int getMaxValidField() {
		return maxValidField;
	}

	public void setMaxValidField(int maxValidField) {
		this.maxValidField = maxValidField;
	}

	public int getBitmapField() {
		return bitmapField;
	}

	public void setBitmapField(int bitmapField) {
		this.bitmapField = bitmapField;
	}

	public int getFirstField() {
		return firstField;
	}

	public void setFirstField(int firstField) {
		this.firstField = firstField;
	}

	public boolean isEmitBitmap() {
		return emitBitmap;
	}

	public void setEmitBitmap(boolean emitBitmap) {
		this.emitBitmap = emitBitmap;
	}

	public int getHeaderLength() {
		return headerLength;
	}

	public void setHeaderLength(int headerLength) {
		this.headerLength = headerLength;
	}
	
	public Collection<IsoField> getIsoFields() {
		return isoFields;
	}
	
	public void setIsoFields(Collection<IsoField> isoFields) {
		this.isoFields = isoFields;
	}

	public Collection<IsoTemplate> getIsoTemplateChildren() {
		return isoTemplateChildren;
	}

	public void setIsoTemplateChildren(Collection<IsoTemplate> isoTemplateChildren) {
		this.isoTemplateChildren = isoTemplateChildren;
	}

	public String getNameToFiles() {
		return this.isoTemplateId + "_" + this.isoTemplateName;
	}

	@Override
	public String toString() {
		return "{\r\n\t'isoTemplateId': " + this.isoTemplateId + "," +
				"\r\n\t'isoTemplateName': '" + this.isoTemplateName + "'," +
				"\r\n\t'baseOn': " + ((null != this.baseOn) ? this.baseOn.toString() : "{}" )+ "," +
				"\r\n\t'isoTemplateDescription': '" + this.isoTemplateDescription + "'," +
				"\r\n\t'lastUpdated': '" + ((null != this.lastUpdated) ? this.lastUpdated : "") + "'," +
				"\r\n\t'typeVisibility': '" + this.typeVisibility + "'," +
				"\r\n\t'userId': " + ((null != this.userId) ? this.userId.toString() : "{}") + "," +
				"\r\n\t'maxValidField': " + this.maxValidField + "," +
				"\r\n\t'bitmapField': " + this.bitmapField + "," +
				"\r\n\t'firstField': " + this.firstField + "," +
				"\r\n\t'emitBitmap': " + this.emitBitmap + "," +
				"\r\n\t'headerLength': " + this.headerLength+ "," +
				"\r\n\t'isoFields': " + ((null == this.isoFields) ? "{}" : tools.arrToString(this.isoFields)) + "," +
				"\r\n\t'isoTemplateChildren': " + ((null == this.isoTemplateChildren) ? "{}" : tools.arrToString(this.isoTemplateChildren)) + "" +
				"\r\n}";
	}
}
