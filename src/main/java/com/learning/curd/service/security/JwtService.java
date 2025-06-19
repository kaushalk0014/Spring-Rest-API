package com.learning.curd.service.security;


import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	private final String SECURITY_KEY = "265_bit_secret";
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))//1 hour
				.signWith(getSignKey(), SignatureAlgorithm.ES256)
				.compact();
	}
	
	private Key getSignKey() {
		byte [] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);
		return (Key) Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserame(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}
	
	public String extractUserame(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
