package com.ideascale.service;

import java.util.List;
import com.ideascale.entity.Department;

public interface DepartmentService {
	public void saveDepartment(Department department);
	public List<Department> getAllDepartment();
	public Department getDepartmentById(int id);
	public void deleteDepartmentById(int id);
}
