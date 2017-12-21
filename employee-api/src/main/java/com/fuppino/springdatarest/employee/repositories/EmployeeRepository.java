package com.fuppino.springdatarest.employee.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fuppino.springdatarest.employee.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
}
