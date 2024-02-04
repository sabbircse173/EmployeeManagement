package com.ideascale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideascale.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// department LIKE %?1% or
	@Query("from Employee where name LIKE %?1% or gender LIKE %?1% OR address LIKE %?1%")
	public List<Employee> getAllEmployeesBySearch(String search_str);
	
	public List<Employee> findByGender(String gender);
}
