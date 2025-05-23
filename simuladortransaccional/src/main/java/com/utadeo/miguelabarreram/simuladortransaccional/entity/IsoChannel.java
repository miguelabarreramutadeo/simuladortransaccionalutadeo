package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IsoChannel implements Serializable {

	private static final long serialVersionUID = -4303300866822993168L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isoChannelId;
	private String isoChannelName;
	private String isoChannelType;
	private String isoChannelPackager;
	private String isoChannelVisibility;
	private String isoChannelDescription;
	private Timestamp lastUpdated;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "isoChannelOwner", referencedColumnName = "userId")
	private User isoChannelOwner;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	private User updatedBy;
	
	public IsoChannel() {
	}
	
	@JsonCreator
	public IsoChannel(int isoChannelId, String isoChannelName, String isoChannelType, String isoChannelPackager,
			String isoChannelVisibility, String isoChannelDescription, Timestamp lastUpdated, User isoChannelOwner,
			User updatedBy) {
		this.isoChannelId = isoChannelId;
		this.isoChannelName = isoChannelName;
		this.isoChannelType = isoChannelType;
		this.isoChannelPackager = isoChannelPackager;
		this.isoChannelVisibility = isoChannelVisibility;
		this.isoChannelDescription = isoChannelDescription;
		this.lastUpdated = lastUpdated;
		this.isoChannelOwner = isoChannelOwner;
		this.updatedBy = updatedBy;
	}

	public int getIsoChannelId() {
		return isoChannelId;
	}
	public void setIsoChannelId(int isoChannelId) {
		this.isoChannelId = isoChannelId;
	}
	public String getIsoChannelName() {
		return isoChannelName;
	}
	public void setIsoChannelName(String isoChannelName) {
		this.isoChannelName = isoChannelName;
	}
	public String getIsoChannelType() {
		return isoChannelType;
	}
	public void setIsoChannelType(String isoChannelType) {
		this.isoChannelType = isoChannelType;
	}
	public String getIsoChannelPackager() {
		return isoChannelPackager;
	}
	public void setIsoChannelPackager(String isoChannelPackager) {
		this.isoChannelPackager = isoChannelPackager;
	}
	public String getIsoChannelVisibility() {
		return isoChannelVisibility;
	}
	public void setIsoChannelVisibility(String isoChannelVisibility) {
		this.isoChannelVisibility = isoChannelVisibility;
	}
	public String getIsoChannelDescription() {
		return isoChannelDescription;
	}
	public void setIsoChannelDescription(String isoChannelDescription) {
		this.isoChannelDescription = isoChannelDescription;
	}
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public User getIsoChannelOwner() {
		return isoChannelOwner;
	}
	public void setIsoChannelOwner(User isoChannelOwner) {
		this.isoChannelOwner = isoChannelOwner;
	}
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public String toString() {
		return "{\r\n\t'isoChannelId': " + this.isoChannelId + "," +
				"\r\n\t'isoChannelName': '" + this.isoChannelName + "'," +
				"\r\n\t'isoChannelType': '" + this.isoChannelType + "'," +
				"\r\n\t'isoChannelPackager': '" + this.isoChannelPackager + "'," +
				"\r\n\t'isoChannelVisibility': '" + this.isoChannelVisibility + "'," +
				"\r\n\t'isoChannelDescription': '" + this.isoChannelDescription + "'," +
				"\r\n\t'lastUpdated': '" + ((null != this.lastUpdated) ? this.lastUpdated : "") + "'," +
				"\r\n\t'isoChannelOwner': " + ((null != this.isoChannelOwner) ? this.isoChannelOwner.toString() : "{}") + "," +
				"\r\n\t'updatedBy': " + ((null != this.updatedBy) ? this.updatedBy.toString() : "{}") +
				"\r\n}";
	}
}
