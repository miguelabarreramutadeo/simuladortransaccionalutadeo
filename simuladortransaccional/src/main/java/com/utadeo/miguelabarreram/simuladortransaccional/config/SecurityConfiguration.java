package com.utadeo.miguelabarreram.simuladortransaccional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.utadeo.miguelabarreram.simuladortransaccional.service.UserRepositoryUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/*/*.js*", "/*/*.css*", "/*/*.png", "/*/*.jpg", "/*/*.jpeg", "/*/*.png", "/*/*.gif")
				.permitAll()
				.anyRequest()
				.authenticated())
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll())
		.sessionManagement((sessionManagement) -> sessionManagement
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true) );
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserRepositoryUserDetailsService();
	}
}
