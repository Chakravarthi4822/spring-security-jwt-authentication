package com.spring_security_day_two.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security_day_two.entity.AppUser;
import com.spring_security_day_two.service.JwtService;

@RestController
public class LoginController {

	private AuthenticationManager amAuthenticationManager;
	private JwtService jwtService;
	
	@Autowired
	public LoginController(AuthenticationManager amAuthenticationManager, JwtService jwtService) {
		this.amAuthenticationManager = amAuthenticationManager;
		this.jwtService = jwtService;
	}
	
	
	@PostMapping("/login")
	public String login(@RequestBody AppUser user) {
		amAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken
				(
						user.getUsername(),user.getPassword())
				);
		return jwtService.generateTocken(user.getUsername());	
		}
	
	
}
