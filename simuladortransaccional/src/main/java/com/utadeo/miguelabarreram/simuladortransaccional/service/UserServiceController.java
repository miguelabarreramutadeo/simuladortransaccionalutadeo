package com.utadeo.miguelabarreram.simuladortransaccional.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.UserController;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.GenericJson;

import jakarta.mail.MessagingException;

@RestController
public class UserServiceController {

	@Autowired
	UserController userController;

	public UserServiceController() {
	}

	@GetMapping("/users/listUsers")
	public GenericJson users() {
		List<User> ltUsers = new ArrayList<User>();
		userController.users().forEach((User u) -> {
			User updatedBy = new User();
			
			if (null != u.getUpdatedBy())
				updatedBy = userController.getUserById(u.getUpdatedBy().getUserId(), true);
			
			u.setPassword(null);
			u.setUpdatedBy(updatedBy);
			ltUsers.add(u);
		});
		return new GenericJson(ltUsers);
	}

	@PostMapping("/users/saveUser")
	public User saveUser(@RequestBody User user) throws MessagingException {
		User u = new User();
		u.setUserId(1);
		user.setUpdatedBy(u);
		return userController.saveUser(user, "save");
	}
}
