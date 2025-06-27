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
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Integer id) {
		Employee employee = service.getEmployeeById(id);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("/getByName")
	public ResponseEntity<Employee> getEmployeeByName(@RequestParam(name="name") String name) {
		Employee employee = service.getEmployeeByName(name);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> list = service.getAllEmployee();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
}
