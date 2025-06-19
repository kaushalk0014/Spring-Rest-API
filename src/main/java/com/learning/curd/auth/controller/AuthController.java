
package com.learning.curd.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.curd.security.dto.AuthRequest;
import com.learning.curd.security.dto.AuthResponse;
import com.learning.curd.service.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
		Authentication authentication =  authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		jwtService.generateToken(authRequest.getUsername());
		AuthResponse authResponse=null;
		return ResponseEntity.ok(authResponse);
	}
}
