package com.learning.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learning.curd.dao.EmployeeRepositry;
import com.learning.curd.dto.AppUserDTO;
import com.learning.curd.entiry.Employee;
import com.learning.exception.ResourceNotFoundException;

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

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Employee createEmployee(Employee employee) {
		Integer id =repositry.findMaxId();
		id= id==null?1 :id+1;
		employee.setId(id);
		return repositry.saveAndFlush(employee);
	}


	public Employee updateEmployee(Employee employee, Integer id) {
		 Employee existing = getEmployeeById(id);
		 existing.setName(employee.getName());
		 existing.setGender(employee.getGender());
		 existing.setSalary(employee.getSalary());
		return repositry.save(existing);
	}

	public Employee updateGender(Employee employee, Integer id) {
		Employee existing = repositry.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id : "+id));
		existing.setGender(employee.getGender());
		
		existing= repositry.save(existing);
		return existing;
	}

	public void deleteEmployeeById(Integer id) {
		repositry.deleteById(id);
	}

	public AppUserDTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
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
