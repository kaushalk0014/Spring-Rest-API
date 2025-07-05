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
import org.springframework.stereotype.Service;

import com.learning.curd.entiry.AuthRequest;

@Service
public class APIUserDetailsService implements UserDetailsService {

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAuthService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AuthRequest customUserDetails = userService.findByUserName(username);

		customUserDetails.setRoles(Arrays.asList("USER"));
		
		System.out.println("============"+customUserDetails);
		
		  return new User(
				  customUserDetails.getUsername(),
				  customUserDetails.getPassword(),
				  customUserDetails.getRoles().stream()
			            .map(role -> new SimpleGrantedAuthority(role))
			            .collect(Collectors.toList())
			    );
	}

	private Collection<? extends GrantedAuthority> getAuthorities(AuthRequest user) {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("USER"))
				.collect(Collectors.toList());
	}

}
