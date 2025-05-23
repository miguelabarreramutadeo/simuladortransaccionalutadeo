package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.Tools;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class IsoMsgTpl implements Serializable {

	@Transient
	@Autowired
	Tools tools;
	
	private static final long serialVersionUID = 5196651150338288179L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isoMsgTplId;
	private String isoMsgTplName;
	private String isoMsgType;
	
	@ManyToOne(targetEntity = IsoTemplate.class)
	@JoinColumn(name = "isoTemplateId")
	private IsoTemplate isoTemplateId;
	private String isoMsgDesc;
	private String isoMsgVsby;

	@Transient
	private IsoMsgTpl isoMsgTplBsOn;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "isoMsgTplOwner", referencedColumnName = "userId")
	private User isoMsgTplOwner;
	private Timestamp lastUpdated;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	private User updatedBy;
	
	@JsonIgnore
	@OneToMany(targetEntity = IsoMsgFldsTpl.class, mappedBy = "isoMsgFldsTplId", cascade = CascadeType.REMOVE)
	private Collection<IsoMsgFldsTpl> isoMsgFlds;
	
	public IsoMsgTpl() {
	}

	public IsoMsgTpl(int isoMsgTplId, String isoMsgTplName, String isoMsgType, IsoTemplate isoTemplateId,
			String isoMsgDesc, String isoMsgVsby, User isoMsgTplOwner, Timestamp lastUpdated, User updatedBy) {
		this.isoMsgTplId = isoMsgTplId;
		this.isoMsgTplName = isoMsgTplName;
		this.isoMsgType = isoMsgType;
		this.isoTemplateId = isoTemplateId;
		this.isoMsgTplOwner = isoMsgTplOwner;
		this.isoMsgVsby = isoMsgVsby;
		this.isoMsgDesc = isoMsgDesc;
		this.lastUpdated = lastUpdated;
		this.updatedBy = updatedBy;
	}

	public int getIsoMsgTplId() {
		return isoMsgTplId;
	}

	public void setIsoMsgTplId(int isoMsgTplId) {
		this.isoMsgTplId = isoMsgTplId;
	}

	public String getIsoMsgTplName() {
		return isoMsgTplName;
	}

	public void setIsoMsgTplName(String isoMsgTplName) {
		this.isoMsgTplName = isoMsgTplName;
	}

	public String getIsoMsgType() {
		return isoMsgType;
	}

	public void setIsoMsgType(String isoMsgType) {
		this.isoMsgType = isoMsgType;
	}

	public IsoTemplate getIsoTemplateId() {
		return isoTemplateId;
	}

	public void setIsoTemplateId(IsoTemplate isoTemplateId) {
		this.isoTemplateId = isoTemplateId;
	}

	public User getIsoMsgTplOwner() {
		return isoMsgTplOwner;
	}

	public void setIsoMsgTplOwner(User isoMsgTplOwner) {
		this.isoMsgTplOwner = isoMsgTplOwner;
	}
	
	public String getIsoMsgDesc() {
		return isoMsgDesc;
	}
	
	public void setIsoMsgDesc(String isoMsgDesc) {
		this.isoMsgDesc = isoMsgDesc;
	}
	
	public String getIsoMsgVsby() {
		return isoMsgVsby;
	}
	
	public void setIsoMsgVsby(String isoMsgVsby) {
		this.isoMsgVsby = isoMsgVsby;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Collection<IsoMsgFldsTpl> getIsoMsgFlds() {
		return isoMsgFlds;
	}

	public void setIsoMsgFlds(Collection<IsoMsgFldsTpl> isoMsgFlds) {
		this.isoMsgFlds = isoMsgFlds;
	}
	
	public IsoMsgTpl getIsoMsgTplBsOn() {
		return isoMsgTplBsOn;
	}
	
	public void setIsoMsgTplBsOn(IsoMsgTpl isoMsgTplBsOn) {
		this.isoMsgTplBsOn = isoMsgTplBsOn;
	}
	
	@Override
	public String toString() {
		return "{\r\n\t'isoMsgTplId': " + this.isoMsgTplId + "," +
				"\r\n\t'isoMsgTplName': " + this.isoMsgTplName + "," +
				"\r\n\t'isoMsgType': '" + this.isoMsgType + "'," +
				"\r\n\t'isoTemplateId': " + ((null != this.isoTemplateId) ? this.isoTemplateId.toString() : "{}") + "," +
				"\r\n\t'isoMsgDesc': '" + this.isoMsgDesc + "'," +
				"\r\n\t'isoMsgVsby': " + this.isoMsgVsby + "," +
				"\r\n\t'isoMsgTplBsOn': " + ((null != this.isoMsgTplBsOn) ? this.isoMsgTplBsOn.toString() : "{}") + "," +
				"\r\n\t'isoMsgTplOwner': " + ((null != this.isoMsgTplOwner) ? this.isoMsgTplOwner.toString() : "{}") + "," +
				"\r\n\t'lastUpdated': '" + ((null != this.lastUpdated) ? this.lastUpdated : "") + "'," +
				"\r\n\t'updatedBy': " + ((null != this.updatedBy) ? this.updatedBy.toString() : "{}") + "" +
				"\r\n}";
	}
}
