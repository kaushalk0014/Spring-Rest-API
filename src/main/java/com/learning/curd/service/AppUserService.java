package com.learning.curd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.curd.dao.AppUserRepositry;
import com.learning.curd.dto.AppUserDTO;
import com.learning.curd.entiry.AppUser;
import com.learning.utils.BeanUtils;

@Service
public class AppUserService {
	
	@Autowired
	private AppUserRepositry appUserRepositry;

	public AppUserDTO getUserById(Integer id) {
		Optional<AppUser> appUser= appUserRepositry.findById(id);
		return BeanUtils.userEntiryToDto(appUser);
	}

	public AppUserDTO getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AppUserDTO> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
