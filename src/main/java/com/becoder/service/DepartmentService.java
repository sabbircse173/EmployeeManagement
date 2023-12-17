package com.becoder.service;

import java.util.List;
import com.becoder.entity.Department;

public interface DepartmentService {
	public Department saveDepartment(Department department);
	public List<Department> getAllDepartment();
	public Department getDepartmentById(int id);
	public boolean deleteDepartmentById(int id);
}
