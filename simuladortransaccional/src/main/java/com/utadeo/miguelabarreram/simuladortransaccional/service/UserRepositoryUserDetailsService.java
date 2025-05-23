package com.utadeo.miguelabarreram.simuladortransaccional.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.utadeo.miguelabarreram.simuladortransaccional.entity.User;
import com.utadeo.miguelabarreram.simuladortransaccional.entity.controller.UserController;

public class UserRepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserController userController;
	
	public UserRepositoryUserDetailsService() {
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userController.findUserByUserName(username);
		if (null == user) 
			throw new UsernameNotFoundException("The username " + username + " is not found");
		return new CustomUserDetails(user);
	}
	
	static final class CustomUserDetails extends User implements UserDetails {
		
		private static final long serialVersionUID = -6193653955568695515L;
		private static final List<GrantedAuthority> ROLE_USER = Collections.unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));
		
		public CustomUserDetails(User user) {
			super(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getRoleId(), user.isEnabled(), user.getLastUpdated(), user.getLastLogin(), user.getLastFailedLogin(), user.getCountFailedLogin(), user.getUpdatedBy(), user.getChildren());
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return ROLE_USER;
		}
		
		@Override
		public String getUsername() {
			return super.getUserName();
		}
		
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}
		
		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
	}
}
