package com.becoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.becoder.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("from Employee where department = ?1")
	public List<Employee> getAllEmployeeBySearch(String department);
}

