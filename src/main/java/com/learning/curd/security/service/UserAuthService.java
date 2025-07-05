package com.learning.curd.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.curd.dto.AppUserDTO;
import com.learning.curd.entiry.AuthRequest;
import com.learning.curd.security.dao.UserAuthRepositry;
import com.learning.exception.ResourceNotFoundException;
import com.learning.exception.UserCreationException;

@Service
public class UserAuthService {

	@Autowired
	private UserAuthRepositry userRepositry;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void registerUser(AppUserDTO user) {
		 
		Optional<AuthRequest> dbuser = userRepositry.findById(user.getUsername());
		
		if(dbuser.isPresent()) {
			throw new UserCreationException("Usrename : "+user.getUsername()+ " already exist, Try with other username");
		}
		
		String encodedPwd = passwordEncoder.encode(user.getPassword());
		
		 AuthRequest customUserDetails=new AuthRequest();
		 customUserDetails.setUsername(user.getUsername());
		 customUserDetails.setPassword(encodedPwd);
		 customUserDetails.setRoles(user.getRoles());
		 userRepositry.save(customUserDetails);
	}

	public AuthRequest findByUserName(String username) {
		AuthRequest user= userRepositry.findById(username)
				.orElseThrow(()->new ResourceNotFoundException("User not found"));
		return user;
	}
	
}
