package com.ideascale.repository;

import java.util.List;

import com.ideascale.data.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideascale.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeRepositoryCustom {
    List<Employee> findByGender(Gender gender);
}
