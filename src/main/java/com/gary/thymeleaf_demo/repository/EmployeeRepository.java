package com.gary.thymeleaf_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gary.thymeleaf_demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
