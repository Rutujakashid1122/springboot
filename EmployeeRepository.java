package com.spring.springbootdataapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springbootdataapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
