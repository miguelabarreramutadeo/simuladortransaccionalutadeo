package com.utadeo.miguelabarreram.simuladortransaccional.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SecurityConfiguration.class);
	}
	
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
