package com.tushar.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tushar.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{

//	Employee findByUsername(String username);
	
}
