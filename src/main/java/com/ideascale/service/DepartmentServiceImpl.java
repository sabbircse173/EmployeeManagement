package com.ideascale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideascale.entity.Department;
import com.ideascale.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);
		if(departmentOptional.isPresent()) {
			return departmentOptional.get();
		}
		return null;
	}

	@Override
	public boolean deleteDepartmentById(int id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);
		if(departmentOptional.isPresent()) {
			departmentRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
