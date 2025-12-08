package com.ideascale.service;

import java.util.List;

import com.ideascale.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ideascale.entity.Department;
import com.ideascale.repository.DepartmentRepository;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Nonnull private final DepartmentRepository departmentRepository;
	@Override
	public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
		return departmentRepository.findById(id).orElse(null);
    }

	@Override
	public void deleteDepartmentById(int id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department Not Found by ID: " + id);
        }
        departmentRepository.delete(department);
    }

}
