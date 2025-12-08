package com.ideascale.service;

import java.util.List;
import java.util.Optional;

import com.ideascale.data.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ideascale.entity.Employee;
import com.ideascale.repository.EmployeeRepository;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Nonnull private final EmployeeRepository employeeRepository;
	
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
		return employeeRepository.findById(id).orElse(null);
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
	public List<Employee> getAllEmployeeByTerm(String search_str) {
		return employeeRepository.getAllEmployeesByTerm(search_str);
	}

	@Override
	public List<Employee> getAllEmployeeByGender(Gender gender) {
		return employeeRepository.findByGender(gender);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return employeeRepository.getEmployeesByName(name);
	}

}
