package com.spring_security_day_two.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	
	private JwtService jwtService;
	private CustomeUserDetailsService userDetailsService;
	
	@Autowired
	public JwtFilter(JwtService jwtService, CustomeUserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
		String authHeader = request.getHeader("Authorization");
	    System.out.println("Authorization Header: " + authHeader);
	    
	    if(authHeader !=null && authHeader.startsWith("Bearer")) 
	    {
	    	String token=authHeader.substring(7);
	    	String username=jwtService.extractUsername(token);
	    	//
	    	//
	    	UserDetails userDetails =userDetailsService.loadUserByUsername(username);

	    	if (jwtService.validateToken(token, userDetails)) {

	            UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(
	                            userDetails,
	                            null,
	                            userDetails.getAuthorities()
	                    );

	            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)
	            );

	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }

	    }
	    
	    filterChain.doFilter(request, response);
	}
}


//System.out.println("Database username: " + userDetails.getUsername());
//System.out.println("User authorities: " + userDetails.getAuthorities());
////
////
//System.out.println(" JWT Username "+username);
//System.out.println(" JWT Tocken "+tocken);