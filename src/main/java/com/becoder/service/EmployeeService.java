package com.becoder.service;

import java.util.List;

import com.becoder.entity.Employee;

public interface EmployeeService {
	public Employee saveEmployee(Employee emp);
	public List<Employee> getAllEmployee();
	public List<Employee> getAllEmployeeBySearch(String search_str);
	public List<Employee> getAllEmployeeByGender(String gender);
	public Employee getEmployeeById(int id);
	public boolean deleteEmployeeById(int id);
}
