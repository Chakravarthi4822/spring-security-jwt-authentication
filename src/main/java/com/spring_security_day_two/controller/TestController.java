package com.spring_security_day_two.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/hello")
    public String hello() {
        return "Hello Spring Security i am Chakravarthi";
    }

	@GetMapping("/admin")
	public String admin() {
	    return "Welcome Admin";
	}
}
