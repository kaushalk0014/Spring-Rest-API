package com.learning.curd.security.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.curd.entiry.CustomUserDetails;
import com.learning.curd.service.UserService;

@Service
public class APIUserDetailsService implements UserDetailsService {

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CustomUserDetails customUserDetails = userService.findByUserName(username);

		customUserDetails.setRoles(Arrays.asList("Admin", "User"));
		
		System.out.println(customUserDetails);
		
		  return new org.springframework.security.core.userdetails.User(
				  customUserDetails.getUsername(),
				  customUserDetails.getPassword(),
				  customUserDetails.getRoles().stream()
			            .map(role -> new SimpleGrantedAuthority(role))
			            .collect(Collectors.toList())
			    );
	}

	private Collection<? extends GrantedAuthority> getAuthorities(CustomUserDetails user) {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("USER"))
				.collect(Collectors.toList());
	}

}
