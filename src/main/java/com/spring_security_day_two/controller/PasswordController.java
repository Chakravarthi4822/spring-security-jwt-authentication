package com.spring_security_day_two.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
	
	/*
	 * VARIABLE LEVEL OBJECT INJECTION
	 */
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/password")
    public String password() {
        return passwordEncoder.encode("chakri@123");
    }
    

	  
	
}
