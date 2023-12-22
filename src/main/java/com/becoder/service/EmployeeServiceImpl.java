package com.becoder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoder.entity.Employee;
import com.becoder.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if(employeeOptional.isPresent()) {
			return employeeOptional.get();
		}
		return null;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if(employeeOptional.isPresent()) {
			employeeRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> getAllEmployeeBySearch(String search_str) {
		return employeeRepository.getAllEmployeesBySearch(search_str);
	}

}
