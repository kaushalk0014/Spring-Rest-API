package com.learning.curd.security.service;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		System.out.println("------------------login-----------");
		CustomUserDetails customUserDetails= userService.findByUserName(username);
		
        //if ("kaushal".equals(username)) {
            return User.builder()
                    .username(customUserDetails.getUsername())
                    .password(passwordEncoder.encode(customUserDetails.getPassword()))   
                    .roles("USER")
                    .build();
       // }
       // throw new UsernameNotFoundException("User not found");
	}

	

}
