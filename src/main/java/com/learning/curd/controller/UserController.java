package com.learning.curd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.curd.dto.AppUserDTO;
import com.learning.curd.security.service.UserAuthService;
import com.learning.curd.service.AppUserService;
import com.learning.curd.service.EmployeeService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private UserAuthService userService;
	
	@Autowired
	private AppUserService appUserService;;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody AppUserDTO user){
		userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Registetion done successfully");
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppUserDTO> getUserById(@PathVariable(name = "id") Integer id) {
		AppUserDTO user = appUserService.getUserById(id);

		return new ResponseEntity<AppUserDTO>(user, HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<AppUserDTO> getUserByName(@RequestParam(name="name") String name) {
		AppUserDTO user = appUserService.getUserByName(name);
		return new ResponseEntity<AppUserDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AppUserDTO>> getAllUser(){
		List<AppUserDTO> list = appUserService.getAllUser();
		return new ResponseEntity<List<AppUserDTO>>(list, HttpStatus.OK);
	}
}
