package com.utadeo.miguelabarreram.simuladortransaccional.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = -3086878994671366961L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	private String roleName;
	
	public Role() {
	}
	
	public Role(int roleId) {
		this.roleId = roleId;
	}

	@JsonCreator
	public Role(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return "{\r\n\t'roleId': " + this.roleId + "," +
				"\r\n\t'roleName': '" + this.roleName + "'\r\n}";
	}
}
