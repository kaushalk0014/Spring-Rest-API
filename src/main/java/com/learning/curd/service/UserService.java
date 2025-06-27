package com.learning.curd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.curd.dao.UserRepositry;
import com.learning.curd.dto.AppUser;
import com.learning.curd.entiry.CustomUserDetails;
import com.learning.exception.ResourceNotFoundException;
import com.learning.exception.UserCreationException;

@Service
public class UserService {

	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void registerUser(AppUser user) {
		 
		Optional<CustomUserDetails> dbuser = userRepositry.findById(user.getUsername());
		
		//System.out.println(passwordEncoder.matches(user.getPassword(), dbuser.get().getPassword()));
		if(dbuser.isPresent()) {
			throw new UserCreationException("Usrename : "+user.getUsername()+ " already exist, Try with other username");
		}
		
		String password=passwordEncoder.encode(user.getPassword());
		
		 CustomUserDetails customUserDetails=new CustomUserDetails();
		 customUserDetails.setUsername(user.getUsername());
		 customUserDetails.setPassword(password);
		 customUserDetails.setRoles(user.getRoles());
		 userRepositry.save(customUserDetails);
	}

	public CustomUserDetails findByUserName(String username) {
		CustomUserDetails user= userRepositry.findById(username)
				.orElseThrow(()->new ResourceNotFoundException("User not found"));
		return user;
	}
	
}
