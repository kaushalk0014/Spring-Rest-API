package com.learning.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.curd.dao.EmployeeRepositry;
import com.learning.curd.entiry.Employee;
import com.learning.enception.ResourceNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositry repositry;
	
	public Employee getEmployeeById(Integer id) {
		return repositry.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with id : "+id));
	}

	public Employee getEmployeeByName(String name) {
		return repositry.getEmployeeByName(name);
	}

	public List<Employee> getAllEmployee() {
		return repositry.findAll();
	}

	public Employee createEmployee(Employee employee) {
		Integer id =repositry.findMaxId();
		employee.setId(++id);
		return repositry.save(employee);
	}


	public Employee updateEmployee(Employee employee, Integer id) {
		 Employee existing = getEmployeeById(id);
		 existing.setName(employee.getName());
		 existing.setGender(employee.getName());
		 existing.setSalary(employee.getSalary());
		return repositry.save(existing);
	}

	public Employee updateGender(Employee employee, Integer id) {
		Employee existing = repositry.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id : "+id));
		existing.setGender(employee.getGender());
		
		existing= repositry.save(existing);
		return existing;
	}

}
