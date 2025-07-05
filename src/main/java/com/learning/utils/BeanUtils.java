package com.learning.utils;

import java.util.Optional;

import com.learning.curd.dto.AppUserDTO;
import com.learning.curd.entiry.AppUser;
import com.learning.exception.ResourceNotFoundException;

public class BeanUtils {

	public static AppUserDTO userEntiryToDto(Optional<AppUser> appUser) {
		return appUser.map(user->{
			AppUserDTO appUserDTO=new AppUserDTO();
			appUserDTO.setDob(user.getDob());
			appUserDTO.setEmailId(user.getEmailId());
			appUserDTO.setGender(user.getGender());
			appUserDTO.setPhoneNumber(user.getPhoneNumber());
			appUserDTO.setUsername(user.getUsername());
			return appUserDTO;
		}).orElseThrow(()->new ResourceNotFoundException("User not found"));
	}
}
