package com.spring_security_day_two.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt..secret}")
	private String secret;
	
	
	private Key getKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	/*
	 * Generation of JWT Tocken 
	 */
	public String generateTocken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 10))
				.signWith(getKey())
				.compact();
			
	}
	
	/*
	 * extract user name
	 */
	
	public String extractUsername(String token) {
	    return Jwts.parser()
	            .verifyWith((javax.crypto.SecretKey) getKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}
	
	/*
	 * Validation ---JWT validation checks that a token is authentic, unexpired, and associated with the expected user.
	 */
	
	public boolean validateToken(String token, UserDetails userDetails) {
	    String username = extractUsername(token);

	    return username.equals(userDetails.getUsername());
	}
}
