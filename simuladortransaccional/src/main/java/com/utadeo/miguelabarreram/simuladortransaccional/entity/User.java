package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1313812021402012943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String email;

	@JsonIgnore
	private String password;

	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "roleId")
	private Role roleId;
	private boolean enabled;
	private Timestamp lastUpdated;
	private Timestamp lastLogin;
	private Timestamp lastFailedLogin;
	private int countFailedLogin;
	
	@ManyToOne()
	@JoinColumn(name = "updatedBy")
	private User updatedBy;
	
	@JsonIgnore
	@OneToMany(mappedBy = "updatedBy")
	private Collection<User> children;

	public User() {
	}

	@JsonCreator
	public User(int userId, String userName, String email, String password, Role roleId, boolean enabled) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.enabled = enabled;
	}

	public User(int userId, String userName, String email, String password, Role roleId, boolean enabled,
			Timestamp lastUpdated, Timestamp lastLogin, Timestamp lastFailedLogin, int countFailedLogin, User updatedBy,
			Collection<User> children) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.enabled = enabled;
		this.lastUpdated = lastUpdated;
		this.lastLogin = lastLogin;
		this.lastFailedLogin = lastFailedLogin;
		this.countFailedLogin = countFailedLogin;
		this.updatedBy = updatedBy;
		this.children = children;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getLastFailedLogin() {
		return lastFailedLogin;
	}

	public void setLastFailedLogin(Timestamp lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}

	public int getCountFailedLogin() {
		return countFailedLogin;
	}

	public void setCountFailedLogin(int countFailedLogin) {
		this.countFailedLogin = countFailedLogin;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setChildren(Collection<User> children) {
		this.children = children;
	}
	
	public Collection<User> getChildren() {
		return children;
	}
	
	@Override
	public String toString() {
		return "{\r\n\t'userId': " + this.userId + "," + 
				"\r\n\t'userName': '" + this.userName + "'," + 
				"\r\n\t'email': '" + this.email + "'," + 
				"\r\n\t'roleId': " + ((null != this.roleId) ? this.roleId.toString() : "{}") + "," + 
				"\r\n\t'enabled': " + this.enabled + "," + 
				"\r\n\t'lastUpdated': '" + ((null != this.lastUpdated) ? this.lastUpdated : "") + "'," + 
				"\r\n\t'lastLogin': '" + ((null != this.lastLogin) ? this.lastLogin : "") + "'," + 
				"\r\n\t'lastFailedLogin': '" + ((null != this.lastFailedLogin) ? this.lastFailedLogin : "") + "'," + 
				"\r\n\t'countFailedLogin': " + this.countFailedLogin + "," + 
				"\r\n\t'updatedBy': " + ((null != this.updatedBy) ? this.updatedBy.toString() : "{}") + 
				"\r\n}";
	}

}
