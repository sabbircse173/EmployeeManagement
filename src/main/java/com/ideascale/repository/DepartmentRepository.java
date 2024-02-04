package com.ideascale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideascale.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
