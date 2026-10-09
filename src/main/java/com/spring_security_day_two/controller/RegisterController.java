package com.spring_security_day_two.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security_day_two.entity.AppUser;
import com.spring_security_day_two.repository.AppUserRepository;

@RestController
public class RegisterController {
	
	private PasswordEncoder passwordEncoder;
	private AppUserRepository appUserRepository;
	
	@Autowired
	public RegisterController(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
		this.passwordEncoder = passwordEncoder;
		this.appUserRepository = appUserRepository;
	}
	
	@PostMapping("/register")
    public String register(@RequestBody AppUser user) {

       user.setPassword(passwordEncoder.encode(user.getPassword()));
       appUserRepository.save(user);
        return "User registered successfully";
    }
	
	

}
