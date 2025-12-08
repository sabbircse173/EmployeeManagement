package com.ideascale.repository;

import com.ideascale.data.Gender;
import com.ideascale.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> getEmployeesByName(String name);
    
    List<Employee> getEmployeesByDepartmentName(String departmentName);
    
    List<Employee> getEmployeesByGenderAndDepartment(Gender gender, String departmentName);
    
    List<Employee> getEmployeesJoinedAfter(Date joiningDate);
    
    List<Employee> getEmployeesByAgeRange(Date fromBirthDate, Date toBirthDate);
    
    Page<Employee> searchEmployees(String searchTerm, Pageable pageable);
    
    long countEmployeesByDepartment(String departmentName);

    List<Employee> getAllEmployeesByTerm(String term);
}
