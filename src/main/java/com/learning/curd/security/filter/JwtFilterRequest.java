package com.learning.curd.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learning.curd.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterRequest extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain)
		throws ServletException, IOException {
		
		String userName =null;
		String jwt = null;
		
		final String authHeader = request.getHeader("Authorization");
		
		if(authHeader!=null && authHeader.startsWith("JWT")) {
			jwt = authHeader.substring(7);
			userName = jwtService.extractUsername(authHeader);
			
			System.out.println(userName);
		}
		
		
		
	}

}
