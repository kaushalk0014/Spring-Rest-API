package com.learning.curd.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.curd.entiry.AuthRequest;

@Service
public class APIUserDetailsService implements UserDetailsService {

	@Autowired
	@Lazy
	private UserAuthService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AuthRequest customUserDetails = userService.findByUserName(username);
		
		List<String> roles= new ArrayList<String>();roles.add("ADMIN");
		
		return User
        .withUsername(customUserDetails.getUsername())
        .password(customUserDetails.getPassword())
        .roles( roles.toArray(new String[0]))
        .build();
	}
}
