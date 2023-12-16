package com.becoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoder.entity.Employee;
import com.becoder.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo emprepo;
	
	@Override
	public Employee saveEmployee(Employee emp) {
		return emprepo.save(emp);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return emprepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return emprepo.findById(id).get();
	}

	@Override
	public String deleteEmployeeById(int id) {
		Employee emp = emprepo.findById(id).get();
		if(emp != null) {
			emprepo.delete(emp);
			System.out.println("Deleted successfully");
		}
		return "sorry, something went wrong, can't be deleted";
	}

}
