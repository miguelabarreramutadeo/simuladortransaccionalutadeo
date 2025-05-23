package com.utadeo.miguelabarreram.simuladortransaccional.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.UserController;

@Configuration
public class ApplicationConfiguration {

	@Autowired
	private UserController userController;

	@Bean
	void createUserAdmin() {
		userController.createAdmin();
	}
}
