package com.becoder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoder.entity.Department;
import com.becoder.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo deptrepo;
	@Override
	public Department SaveDepartment(Department department) {
		return deptrepo.save(department);
	}

	@Override
	public List<Department> getAllDepartment() {
		return deptrepo.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
		return deptrepo.findById(id).get();
	}

	@Override
	public String DeleteDeptById(int id) {
		Department dept = deptrepo.findById(id).get();
		if(dept != null) {
			deptrepo.delete(dept);
			System.out.println("Deleted successfully");
		}
		return "sorry, something went wrong, can't be deleted";
	}

}
