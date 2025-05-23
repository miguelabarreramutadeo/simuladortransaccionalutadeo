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
public class IsoNode implements Serializable {

	private static final long serialVersionUID = -1144368756100333080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isoNodeId;
	private String isoNodeName;
	private String isoNodeType;
	private String isoNodeHost;
	private int isoNodePort;
	private String isoNodeVisibility;
	private String isoNodeDescription;
	private long isoNodeTimeOut;
	private Timestamp lastUpdated;
	private boolean isoNodeIsStarted;
	private long isoNodeReconnectDelay;
	
	@ManyToOne(targetEntity = IsoChannel.class)
	@JoinColumn(name = "isoChannelId")
	private IsoChannel isoChannelId;
	@ManyToOne(targetEntity = IsoTemplate.class)
	@JoinColumn(name = "isoTemplateId")
	private IsoTemplate isoTemplateId;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "isoNodeOwner", referencedColumnName = "userId")
	private User isoNodeOwner;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	private User updatedBy;
	
	public IsoNode() {}

	@JsonCreator
	public IsoNode(int isoNodeId, String isoNodeName, String isoNodeType, String isoNodeHost, int isoNodePort, 
			String isoNodeVisibility, String isoNodeDescription, long isoNodeTimeOut, IsoChannel isoChannelId, 
			IsoTemplate isoTemplateId, boolean isoNodeIsStarted, long isoNodeReconnectDelay) {
		this.isoNodeId = isoNodeId;
		this.isoNodeName = isoNodeName;
		this.isoNodeType = isoNodeType;
		this.isoNodeHost = isoNodeHost;
		this.isoNodePort = isoNodePort;
		this.isoNodeVisibility = isoNodeVisibility;
		this.isoNodeDescription = isoNodeDescription;
		this.isoNodeTimeOut = isoNodeTimeOut;
		this.isoChannelId = isoChannelId;
		this.isoTemplateId = isoTemplateId;
		this.isoNodeIsStarted = isoNodeIsStarted;
		this.isoNodeReconnectDelay = isoNodeReconnectDelay;
	}

	public IsoNode(int isoNodeId, String isoNodeName, String isoNodeType, String isoNodeHost, int isoNodePort,
			String isoNodeVisibility, String isoNodeDescription, long isoNodeTimeOut,
			Timestamp lastUpdated, boolean isoNodeIsStarted, long isoNodeReconnectDelay, IsoChannel isoChannelId,
			IsoTemplate isoTemplateId, User isoNodeOwner, User updatedBy) {
		this.isoNodeId = isoNodeId;
		this.isoNodeName = isoNodeName;
		this.isoNodeType = isoNodeType;
		this.isoNodeHost = isoNodeHost;
		this.isoNodePort = isoNodePort;
		this.isoNodeVisibility = isoNodeVisibility;
		this.isoNodeDescription = isoNodeDescription;
		this.isoNodeTimeOut = isoNodeTimeOut;
		this.lastUpdated = lastUpdated;
		this.isoNodeIsStarted = isoNodeIsStarted;
		this.isoNodeReconnectDelay = isoNodeReconnectDelay;
		this.isoChannelId = isoChannelId;
		this.isoTemplateId = isoTemplateId;
		this.isoNodeOwner = isoNodeOwner;
		this.updatedBy = updatedBy;
	}

	public int getIsoNodeId() {
		return isoNodeId;
	}

	public void setIsoNodeId(int isoNodeId) {
		this.isoNodeId = isoNodeId;
	}

	public String getIsoNodeName() {
		return isoNodeName;
	}

	public void setIsoNodeName(String isoNodeName) {
		this.isoNodeName = isoNodeName;
	}

	public String getIsoNodeType() {
		return isoNodeType;
	}

	public void setIsoNodeType(String isoNodeType) {
		this.isoNodeType = isoNodeType;
	}

	public String getIsoNodeHost() {
		return isoNodeHost;
	}

	public void setIsoNodeHost(String isoNodeHost) {
		this.isoNodeHost = isoNodeHost;
	}

	public int getIsoNodePort() {
		return isoNodePort;
	}

	public void setIsoNodePort(int isoNodePort) {
		this.isoNodePort = isoNodePort;
	}

	public String getIsoNodeVisibility() {
		return isoNodeVisibility;
	}

	public void setIsoNodeVisibility(String isoNodeVisibility) {
		this.isoNodeVisibility = isoNodeVisibility;
	}

	public String getIsoNodeDescription() {
		return isoNodeDescription;
	}

	public void setIsoNodeDescription(String isoNodeDescription) {
		this.isoNodeDescription = isoNodeDescription;
	}

	public long getIsoNodeTimeOut() {
		return isoNodeTimeOut;
	}

	public void setIsoNodeTimeOut(long isoNodeTimeOut) {
		this.isoNodeTimeOut = isoNodeTimeOut;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public IsoChannel getIsoChannelId() {
		return isoChannelId;
	}

	public void setIsoChannelId(IsoChannel isoChannelId) {
		this.isoChannelId = isoChannelId;
	}

	public IsoTemplate getIsoTemplateId() {
		return isoTemplateId;
	}

	public void setIsoTemplateId(IsoTemplate isoTemplateId) {
		this.isoTemplateId = isoTemplateId;
	}

	public User getIsoNodeOwner() {
		return isoNodeOwner;
	}

	public void setIsoNodeOwner(User isoNodeOwner) {
		this.isoNodeOwner = isoNodeOwner;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public boolean isIsoNodeIsStarted() {
		return isoNodeIsStarted;
	}
	
	public void setIsoNodeIsStarted(boolean isoNodeIsStarted) {
		this.isoNodeIsStarted = isoNodeIsStarted;
	}
	
	public long getIsoNodeReconnectDelay() {
		return isoNodeReconnectDelay;
	}
	
	public void setIsoNodeReconnectDelay(long isoNodeReconnectDelay) {
		this.isoNodeReconnectDelay = isoNodeReconnectDelay;
	}
	
	public String getNameToFiles() {
		return this.isoNodeId + "_" + this.isoNodeName;
	}
	
	@Override
	public String toString() {
		return "{\r\n\t'isoNodeId': " + this.isoNodeId + "," +
				"\r\n\t'isoNodeName': '" + this.isoNodeName + "'," +
				"\r\n\t'isoNodeType': '" + this.isoNodeType + "'," +
				"\r\n\t'isoNodeHost': '" + this.isoNodeHost + "'," +
				"\r\n\t'isoNodePort': " + this.isoNodePort + "," +
				"\r\n\t'isoNodeIsStarted': " + this.isoNodeIsStarted + "," +
				"\r\n\t'isoNodeVisibility': '" + this.isoNodeVisibility + "'," +
				"\r\n\t'isoNodeDescription': '" + this.isoNodeDescription + "'," +
				"\r\n\t'isoNodeTimeOut': " + this.isoNodeTimeOut + "," +
				"\r\n\t'isoNodeReconnectDelay': " + this.isoNodeReconnectDelay + ", " +
				"\r\n\t'lastUpdated': '" + ((null != this.lastUpdated) ? this.lastUpdated : "''") + "' ," +
				"\r\n\t'isoChannelId': " + ((null != this.isoChannelId) ? this.isoChannelId.toString() : "{}") + "," +
				"\r\n\t'isoTemplateId': " + ((null != this.isoTemplateId) ?this.isoTemplateId.toString() : "{}") + "," +
				"\r\n\t'isoNodeOwner': " + ((null != this.isoNodeOwner) ?this.isoNodeOwner.toString() : "{}") + "," +
				"\r\n\t'updatedBy': " + ((null != this.updatedBy) ?this.updatedBy.toString() : "{}") +
				"\r\n}";
	}
}
