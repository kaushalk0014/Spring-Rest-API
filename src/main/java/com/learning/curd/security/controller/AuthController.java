
package com.learning.curd.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.curd.security.dto.AuthRequestDTO;
import com.learning.curd.security.dto.AuthResponse;
import com.learning.curd.security.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequestDTO authRequest){
		try {
			
			String role= "ADMIN";
			authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
						authRequest.getPassword()));
				
				String jwtToken = jwtService.generateToken(authRequest.getUsername(), role);
				String[] parts = jwtToken.split("\\.");
				if (parts.length != 3) {
				    throw new IllegalArgumentException("Invalid JWT format");
				}
				
				AuthResponse authResponse=new AuthResponse(jwtToken);
				return ResponseEntity.ok(authResponse);
		}catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(new AuthResponse("Invalid username and password"));
		}
	}
	
	 
}
