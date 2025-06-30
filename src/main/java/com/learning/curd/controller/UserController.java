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

import com.learning.curd.dto.AppUser;
import com.learning.curd.dto.CustomUserDetailsDTO;
import com.learning.curd.entiry.CustomUserDetails;
import com.learning.curd.entiry.Employee;
import com.learning.curd.service.EmployeeService;
import com.learning.curd.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody AppUser user){
		userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Registetion done successfully");
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomUserDetailsDTO> getUserById(@PathVariable(name = "id") Integer id) {
		CustomUserDetailsDTO user = service.getUserById(id);

		return new ResponseEntity<CustomUserDetailsDTO>(user, HttpStatus.OK);
	}

	@GetMapping("/getByName")
	public ResponseEntity<CustomUserDetailsDTO> getUserByName(@RequestParam(name="name") String name) {
		CustomUserDetailsDTO user = service.getUserByName(name);
		return new ResponseEntity<CustomUserDetailsDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomUserDetailsDTO>> getAllEmployee(){
		List<CustomUserDetailsDTO> list = service.getAllUser();
		return new ResponseEntity<List<CustomUserDetailsDTO>>(list, HttpStatus.OK);
	}
}
