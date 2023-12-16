package com.becoder.service;

import java.util.List;
import com.becoder.entity.Department;

public interface DepartmentService {
	public Department SaveDepartment(Department department);
	public List<Department> getAllDepartment();
	public Department getDepartmentById(int id);
	public String DeleteDeptById(int id);
}
