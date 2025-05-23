package com.utadeo.miguelabarreram.simuladortransaccional.tools;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_ACTIVE = 1;
	public static final int FAILLED_LOGIN = -1;
	public static final String ADMIN_ROLE = "Admin";
}
