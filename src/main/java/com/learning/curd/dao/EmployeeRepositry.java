package com.learning.curd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.curd.entiry.Employee;

@Repository
public interface EmployeeRepositry extends JpaRepository<Employee, Integer>{

	
	@Query("SELECT e FROM Employee e where e.name= :name")
	public Employee getEmployeeByName(@Param("name") String name);

	@Query("SELECT max(id) FROM Employee")
	public Integer findMaxId();

}
