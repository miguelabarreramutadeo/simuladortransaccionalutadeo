package com.utadeo.miguelabarreram.simuladortransaccional.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;
import com.utadeo.miguelabarreram.simuladortransaccional.security.CurrentUser;

@RestController
public class UserService {

	@GetMapping("/user")
	public User user(@CurrentUser User currentUser) {
		return currentUser;
	}
}
