package com.spring_security_day_two.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_security_day_two.entity.AppUser;
import com.spring_security_day_two.repository.AppUserRepository;
@Service
public class CustomeUserDetailsService  implements UserDetailsService {

	private AppUserRepository repository;
	@Autowired
	public CustomeUserDetailsService(AppUserRepository repository) {
		this.repository = repository;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> ap=repository.findByUserName(username);
		if(ap.isPresent()) {
			AppUser app=ap.get();
			return User.builder()
					.username(app.getUsername())
					.password(app.getPassword())
					.roles(app.getRole())
					.build();
	}
		return null;
		
	}
}
