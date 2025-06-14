package com.learning.curd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.curd.entiry.Employee;
import com.learning.curd.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmpoloyeeController {

	@Autowired
	private EmployeeService service;

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
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		employee = service.createEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee){
		employee = service.updateEmployee(employee, id);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Employee> updateGender(@PathVariable("id") Integer id,  @RequestBody Employee employee){
		employee = service.updateGender(employee, id);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id){
		service.deleteEmployeeById(id);
		return new ResponseEntity<String>("Employee deleted successfully id : "+id, HttpStatus.OK);
	}

}
