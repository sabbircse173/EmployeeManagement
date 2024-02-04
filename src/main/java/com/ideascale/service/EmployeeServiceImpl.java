package com.ideascale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideascale.entity.Employee;
import com.ideascale.repository.EmployeeRepository;

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

	@Override
	public List<Employee> getAllEmployeeByGender(String gender) {
		return employeeRepository.findByGender(gender);
	}

}
