package com.utadeo.miguelabarreram.simuladortransaccional.entity.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.utadeo.miguelabarreram.simuladortransaccional.tools.EmailSender;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.Security;
import com.utadeo.miguelabarreram.simuladortransaccional.tools.Tools;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.Role;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;
import com.utadeo.miguelabarreram.simuladortransaccional.repository.IUserRepository;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;

@Controller
public class UserController {
	
	@Autowired
	IUserRepository iUserRepository;
	@Autowired
	EmailSender emailSender;
	@Autowired
	Security sec;
	@Autowired
	Tools tools;
	
	public UserController() {}
	
	public Iterable<User> users () {
		return iUserRepository.findByUserIdGreaterThan(1);
	}
	
	public User getUserById(int userId, boolean removeSensitiveData) {
		if (userId < 0)
			return null;
		
		Optional<User> opUser = iUserRepository.findById(userId);
		
		User user = opUser.get();
		
		if (removeSensitiveData && null != opUser.get())
			user.setPassword(null);
		return user;
	}
	
	public User getUser(User user, boolean removeSensitiveData) {
		if (null == user)
			return null;
		
		user = iUserRepository.findByEmail(user.getEmail());
		
		if (removeSensitiveData && null != user)
			user.setPassword(null);
		return user;
	}
	
	public User saveUser(User user, String action) throws MessagingException {
		String aux = "";
		User lastUser = iUserRepository.findByUserName(user.getUserName());
		if (null != lastUser) {
			if ("delete".equals(action)) {
				user = lastUser;
			}
			
			user.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			user.setPassword(lastUser.getPassword());
			user.setUserId(lastUser.getUserId());
		} else {
			PasswordEncoder enconder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			user.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			aux = tools.generateAleatoryText(8);
			user.setPassword(enconder.encode(aux));
		}
		
		user = iUserRepository.save(user);
		
		if (null != user && "create".equals(action)) {
			emailSender.sendHtmlMail(user.getEmail(), null, null, "Welcome to JTransactional Simulator", "Hi, thanks for "
					+ "register to our system; "
					+ "use this credentials: <br/> <b>Username:</b>" + user.getUserName() + "<br/> <b>Password:</b>"
					+ aux);
		}
		
		return user;
	}
	
	public void resetAdmin() throws MessagingException {
		User user = iUserRepository.findByUserName("Admin");
		PasswordEncoder enconder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = enconder.encode("Password");
		if (null == user) 
			new User(1, "Administrator", "admin@adminm.com", encodedPassword, new Role(1, "Administrator"), true, new Timestamp(System.currentTimeMillis()), null, null, 0, new User(1, "Administrator", null, null, null, false, null, null, null, 0, null, null), null);
		else {
			user.setRoleId(new Role(1, "Administrator"));
			user.setPassword(encodedPassword);
			user.setLastUpdated(new Timestamp(System.currentTimeMillis()));
			emailSender.sendHtmlMail(user.getEmail(), null, null, "Reset Admin Password", "Hi, the user 'Admin' reseted password ");
		}
		iUserRepository.save(user);
	}
	
	public User createUser(User user) {
		if (null != findUserByUserName(user.getUserName()))		
			throw new EntityExistsException("The User exists");
		return iUserRepository.save(user);
	}
	
	public User findUserByUserName(String userName) {
		return iUserRepository.findByUserName(userName);
	}

	public void createAdmin() {
		if (null != findUserByUserName("Administrator")) 
			return;
		
		PasswordEncoder enconder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = enconder.encode("Password");
		User user = new User(1, "Administrator", "admin@adminm.com", encodedPassword, new Role(1, "Administrator"), true, new Timestamp(System.currentTimeMillis()), null, null, 0, null, null);
		createUser(user);
	}
}
