package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
