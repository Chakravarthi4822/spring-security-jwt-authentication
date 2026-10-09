package com.spring_security_day_two.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring_security_day_two.service.JwtFilter;


@Configuration
@EnableWebSecurity
public class TestConfiguration {
	
	private JwtFilter jwtFilter;
	@Autowired
	public TestConfiguration(JwtFilter jwtFilter) {
	    this.jwtFilter = jwtFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http
		
		/*
		 * csrf disabled because of the REST APIs
		 */
		.csrf(csrf -> csrf.disable())
		
		/*
		 * Stateless authentication means the server does not rely on an HTTP session to remember the user's authentication between requests;
		 *  each protected request supplies its own authentication token.
		 */
		
		.sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		/*
		 * Providing the rules for end points or APIs
		 */
		
		.authorizeHttpRequests(auth -> auth
//				.requestMatchers("/password").permitAll()//clean up
//				.requestMatchers("/register").permitAll()
//				.requestMatchers("/login").permitAll()
				.requestMatchers("/login", "/register", "/password").permitAll()
				.requestMatchers("/hello").hasRole("USER")
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	     return config.getAuthenticationManager();
	 }

	 
	 
	 
}
