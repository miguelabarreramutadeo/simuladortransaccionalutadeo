package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
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
public class IsoField implements Serializable {
	
	@Transient
	@Autowired
	Tools tools;

	private static final long serialVersionUID = 3640047671959050045L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isoFieldId;
	private int isoFieldNr;
	private String isoFieldName;
	private String isoFieldClass;
	private int isoFieldLength;
	private boolean isoFieldPad;
	private boolean isoHasSubFields;
	private boolean isoEmitBitmap;
	private int isoMaxValidField;
	private int isoBitmapField;
	private int isoFirstField;
	private int isoHeaderLength;
	private String isoFieldTypePad;
	
	@ManyToOne()
	@JoinColumn(name = "isoParentField")
	private IsoField isoParentField;
	
	@JsonBackReference
	@OneToMany(mappedBy = "isoParentField")
	private Collection<IsoField> isoFieldChildren;
	
	@ManyToOne(targetEntity = IsoTemplate.class)
	@JoinColumn(name = "isoTemplateId")
	private IsoTemplate isoTemplateId;
	
	public IsoField() {
	}
	
	public IsoField(IsoField isoField, IsoTemplate isoTemplate, IsoField isoFieldParent) {
		this.isoFieldNr = isoField.getIsoFieldNr();
		this.isoFieldName = isoField.getIsoFieldName();
		this.isoFieldClass = isoField.getIsoFieldClass();
		this.isoFieldLength = isoField.getIsoFieldLength();
		this.isoFieldPad = isoField.isIsoFieldPad();
		this.isoHasSubFields = isoField.isIsoHasSubFields();
		this.isoEmitBitmap = isoField.isIsoEmitBitmap();
		this.isoMaxValidField = isoField.getIsoMaxValidField();
		this.isoBitmapField = isoField.getIsoBitmapField();
		this.isoFirstField = isoField.getIsoFirstField();
		this.isoHeaderLength = isoField.getIsoHeaderLength();
		this.isoFieldTypePad = isoField.getIsoFieldTypePad();
		this.isoParentField = isoFieldParent;
		this.isoTemplateId = isoTemplate;
	}

	@JsonCreator
	public IsoField(int isoFieldId, int isoFieldNr, String isoFieldName, String isoFieldClass, int isoFieldLength,
			boolean isoFieldPad, boolean isoHasSubFields, boolean isoEmitBitmap, int isoMaxValidField, 
			int isoBitmapField, int isoFirstField, int isoHeaderLength, IsoField isoParentField, IsoTemplate isoTemplateId,
			Collection<IsoField> isoFieldChildren, String isoFieldTypePad) {
		this.isoFieldId = isoFieldId;
		this.isoFieldNr = isoFieldNr;
		this.isoFieldName = isoFieldName;
		this.isoFieldClass = isoFieldClass;
		this.isoFieldLength = isoFieldLength;
		this.isoFieldPad = isoFieldPad;
		this.isoHasSubFields = isoHasSubFields;
		this.isoEmitBitmap = isoEmitBitmap;
		this.isoMaxValidField = isoMaxValidField;
		this.isoBitmapField = isoBitmapField;
		this.isoFirstField = isoFirstField;
		this.isoHeaderLength = isoHeaderLength;
		this.isoParentField = isoParentField;
		this.isoTemplateId = isoTemplateId;
		this.isoFieldChildren = isoFieldChildren;
		this.isoFieldTypePad = isoFieldTypePad;
	}
	
	public IsoField(int isoFieldId, int isoFieldNr, String isoFieldName, String isoFieldClass, int isoFieldLength,
			boolean isoFieldPad, boolean isoHasSubFields, boolean isoEmitBitmap, int isoMaxValidField,
			int isoBitmapField, int isoFirstField, int isoHeaderLength, IsoField isoParentField,
			Collection<IsoField> isoFieldChildren, IsoTemplate isoTemplateId, String isoFieldTypePad) {
		this.isoFieldId = isoFieldId;
		this.isoFieldNr = isoFieldNr;
		this.isoFieldName = isoFieldName;
		this.isoFieldClass = isoFieldClass;
		this.isoFieldLength = isoFieldLength;
		this.isoFieldPad = isoFieldPad;
		this.isoHasSubFields = isoHasSubFields;
		this.isoEmitBitmap = isoEmitBitmap;
		this.isoMaxValidField = isoMaxValidField;
		this.isoBitmapField = isoBitmapField;
		this.isoFirstField = isoFirstField;
		this.isoHeaderLength = isoHeaderLength;
		this.isoParentField = isoParentField;
		this.isoFieldChildren = isoFieldChildren;
		this.isoTemplateId = isoTemplateId;
		this.isoFieldTypePad = isoFieldTypePad;
	}

	public int getIsoFieldId() {
		return isoFieldId;
	}

	public void setIsoFieldId(int isoFieldId) {
		this.isoFieldId = isoFieldId;
	}

	public int getIsoFieldNr() {
		return isoFieldNr;
	}

	public void setIsoFieldNr(int isoFieldNr) {
		this.isoFieldNr = isoFieldNr;
	}

	public String getIsoFieldName() {
		return isoFieldName;
	}

	public void setIsoFieldName(String isoFieldName) {
		this.isoFieldName = isoFieldName;
	}

	public String getIsoFieldClass() {
		return isoFieldClass;
	}

	public void setIsoFieldClass(String isoFieldClass) {
		this.isoFieldClass = isoFieldClass;
	}

	public int getIsoFieldLength() {
		return isoFieldLength;
	}

	public void setIsoFieldLength(int isoFieldLength) {
		this.isoFieldLength = isoFieldLength;
	}

	public boolean isIsoFieldPad() {
		return isoFieldPad;
	}

	public void setIsoFieldPad(boolean isoFieldPad) {
		this.isoFieldPad = isoFieldPad;
	}

	public boolean isIsoHasSubFields() {
		return isoHasSubFields;
	}

	public void setIsoHasSubFields(boolean isoHasSubFields) {
		this.isoHasSubFields = isoHasSubFields;
	}

	public boolean isIsoEmitBitmap() {
		return isoEmitBitmap;
	}

	public void setIsoEmitBitmap(boolean isoEmitBitmap) {
		this.isoEmitBitmap = isoEmitBitmap;
	}

	public int getIsoMaxValidField() {
		return isoMaxValidField;
	}

	public void setIsoMaxValidField(int isoMaxValidField) {
		this.isoMaxValidField = isoMaxValidField;
	}

	public int getIsoBitmapField() {
		return isoBitmapField;
	}

	public void setIsoBitmapField(int isoBitmapField) {
		this.isoBitmapField = isoBitmapField;
	}

	public int getIsoFirstField() {
		return isoFirstField;
	}

	public void setIsoFirstField(int isoFirstField) {
		this.isoFirstField = isoFirstField;
	}

	public int getIsoHeaderLength() {
		return isoHeaderLength;
	}

	public void setIsoHeaderLength(int isoHeaderLength) {
		this.isoHeaderLength = isoHeaderLength;
	}

	public IsoField getIsoParentField() {
		return isoParentField;
	}

	public void setIsoParentField(IsoField isoParentField) {
		this.isoParentField = isoParentField;
	}

	public IsoTemplate getIsoTemplateId() {
		return isoTemplateId;
	}

	public void setIsoTemplateId(IsoTemplate isoTemplateId) {
		this.isoTemplateId = isoTemplateId;
	}
	
	public Collection<IsoField> getIsoFieldChildren() {
		return isoFieldChildren;
	}
	
	public void setIsoFieldChildren(Collection<IsoField> isoFieldChildren) {
		this.isoFieldChildren = isoFieldChildren;
	}
	
	public String getIsoFieldTypePad() {
		return this.isoFieldTypePad;
	}
	
	public void setIsoFieldTypePad(String isoFieldTypePad) {
		this.isoFieldTypePad = isoFieldTypePad;
	}
	
	public String toString() {
		return "{\r\n\t'isoFieldId': " + this.isoFieldId + "," +
				"\r\n\t'isoFieldNr': " + this.isoFieldNr + "," +
				"\r\n\t'isoFieldName': '" + this.isoFieldName + "'," +
				"\r\n\t'isoFieldClass': '" + this.isoFieldClass + "'," +
				"\r\n\t'isoFieldLength': " + this.isoFieldLength + "," +
				"\r\n\t'isoFieldPad': " + this.isoFieldPad + "," +
				"\r\n\t'isoFieldTypePad': " + this.isoFieldTypePad + "," +
				"\r\n\t'isoHasSubFields': " + this.isoHasSubFields + "," +
				"\r\n\t'isoEmitBitmap': " + this.isoEmitBitmap + "," +
				"\r\n\t'isoMaxValidField': " + this.isoMaxValidField + "," +
				"\r\n\t'isoBitmapField': " + this.isoBitmapField + "," +
				"\r\n\t'isoFirstField': " + this.isoFirstField + "," +
				"\r\n\t'isoHeaderLength': " + this.isoHeaderLength + "," +
				"\r\n\t'isoParentField': " + (null != this.isoParentField ? this.isoParentField.toString() : "{}") + "," + 
				"\r\n\t'isoTemplateId': " + (null != this.isoTemplateId ? this.isoTemplateId.toString() : "{}") + "," +
				"\r\n\t'isoFieldChildren': " + ((null == this.isoFieldChildren) ? "{}" : tools.arrToString(this.isoFieldChildren)) + "" +
				"\r\n}";
	}
}
