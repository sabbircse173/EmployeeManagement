package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
