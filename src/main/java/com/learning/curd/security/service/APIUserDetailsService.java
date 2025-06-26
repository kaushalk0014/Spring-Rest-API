package com.learning.curd.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.curd.dao.UserRepositry;
import com.learning.curd.entiry.CustomUserDetails;
import com.learning.curd.security.dto.AuthRequest;

@Service
public class APIUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepositry userRepositry;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
        if ("kaushal".equals(username)) {
            return User.builder()
                    .username("kaushal")
                    .password(passwordEncoder.encode(""))   
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
	}

	public void registerUser(AuthRequest user) {
	 
		Optional<CustomUserDetails> dbuser = userRepositry.findById(user.getUsername());
		
		if(dbuser.isPresent()) {
			throw new RuntimeException("User already exist with usrename : "+user.getUsername());
		}
		
		String password=passwordEncoder.encode(user.getPassword());
		System.out.println(password);
		
		 CustomUserDetails customUserDetails=new CustomUserDetails();
		 customUserDetails.setEmailId(user.getUsername());
		 customUserDetails.setPassword(password);
		 userRepositry.save(customUserDetails);
	}
	

}
