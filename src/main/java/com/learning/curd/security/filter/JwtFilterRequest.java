package com.learning.curd.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learning.curd.security.service.APIUserDetailsService;
import com.learning.curd.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterRequest extends OncePerRequestFilter{
	
	@Autowired
	@Lazy
	private JwtService jwtService;
	
	@Autowired
	@Lazy
	private APIUserDetailsService apiUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain)
		throws ServletException, IOException {
		
		String userName =null;
		String jwt = null;
		
		final String authHeader = request.getHeader("Authorization");
		
		if(authHeader!=null && authHeader.startsWith("JWT ")) {
			jwt = authHeader.substring(4);
			userName = jwtService.extractUsername(jwt);
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.apiUserDetailsService.loadUserByUsername(userName);
			
			if(jwtService.validateToken(jwt, userDetails)) {
			
				UsernamePasswordAuthenticationToken  authenticationToken= new 
						UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
