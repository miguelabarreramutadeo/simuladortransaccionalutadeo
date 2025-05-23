package com.utadeo.miguelabarreram.simuladortransaccional.tools;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Security {

	public Security() {
	}
	
	public String encrypted(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());	
			value = Base64.encodeBase64String(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
