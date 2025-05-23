package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IsoMsgFldsTpl implements Serializable {

	private static final long serialVersionUID = -6048737613688770912L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isoMsgFldsTplId;
	
	@ManyToOne()
	@JoinColumn(name = "isoMsgTplId")
	private IsoMsgTpl isoMsgTplId;
	
	@ManyToOne()
	@JoinColumn(name = "isoFieldId")
	private IsoField isoFieldId;
	private boolean isRequired;
	private boolean isValAuto;
	private boolean isValFixed;
	private String isoMsgFldsTplVal;
	
	public IsoMsgFldsTpl() {
	}

	@JsonCreator
	public IsoMsgFldsTpl(int isoMsgFldsTplId, IsoMsgTpl isoMsgTplId, IsoField isoFieldId, boolean isRequired, 
			boolean isValAuto, boolean isValFixed, String isoMsgFldsTplVal) {
		this.isoMsgFldsTplId = isoMsgFldsTplId;
		this.isoMsgTplId = isoMsgTplId;
		this.isoFieldId = isoFieldId;
		this.isValAuto = isValAuto;
		this.isValFixed = isValFixed;
		this.isoMsgFldsTplVal = isoMsgFldsTplVal;
	}

	public IsoMsgFldsTpl(IsoMsgTpl isoMsgTplId, IsoField isoFieldId, boolean isRequired, boolean isValAuto, 
			boolean isValFixed, String isoMsgFldsTplVal) {
		this.isoMsgTplId = isoMsgTplId;
		this.isoFieldId = isoFieldId;
		this.isRequired = isRequired;
		this.isValAuto = isValAuto;
		this.isValFixed = isValFixed;
		this.isoMsgFldsTplVal = isoMsgFldsTplVal;
	}

	public int getIsoMsgFldsTplId() {
		return isoMsgFldsTplId;
	}

	public void setIsoMsgFldsTplId(int isoMsgFldsTplId) {
		this.isoMsgFldsTplId = isoMsgFldsTplId;
	}

	public IsoMsgTpl getIsoMsgTplId() {
		return isoMsgTplId;
	}

	public void setIsoMsgTplId(IsoMsgTpl isoMsgTplId) {
		this.isoMsgTplId = isoMsgTplId;
	}

	public IsoField getIsoFieldId() {
		return isoFieldId;
	}

	public void setIsoFieldId(IsoField isoFieldId) {
		this.isoFieldId = isoFieldId;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isValAuto() {
		return isValAuto;
	}

	public void setValAuto(boolean isValAuto) {
		this.isValAuto = isValAuto;
	}

	public boolean isValFixed() {
		return isValFixed;
	}

	public void setValFixed(boolean isValFixed) {
		this.isValFixed = isValFixed;
	}

	public String getIsoMsgFldsTplVal() {
		return isoMsgFldsTplVal;
	}

	public void setIsoMsgFldsTplVal(String isoMsgFldsTplVal) {
		this.isoMsgFldsTplVal = isoMsgFldsTplVal;
	}
	
	@Override
	public String toString() {
		return "{\r\n\t'isoMsgFldsTplIdId': " + this.isoMsgFldsTplId + "," +
				"\r\n\t'isoMsgTplId': " + ((null != this.isoMsgTplId) ? this.isoMsgTplId.toString(): "{}") + "," +
				"\r\n\t'isoFieldId': " + ((null != this.isoFieldId) ? this.isoFieldId.toString() : "{}") + "," +
				"\r\n\t'isRequired': '" + this.isRequired + "'," +
				"\r\n\t'isValAuto': '" + this.isValAuto + "'," +
				"\r\n\t'isValFixed': '" + this.isValFixed + "'," +
				"\r\n\t'isoMsgFldsTplVal': " + this.isoMsgFldsTplVal + "" +
				"\r\n}";
	}
}
