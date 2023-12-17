package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

