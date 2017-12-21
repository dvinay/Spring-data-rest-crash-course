package com.example.spring.department.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.spring.department.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long>{
}
