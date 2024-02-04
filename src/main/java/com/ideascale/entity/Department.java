package com.ideascale.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


import java.util.List;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Pattern(regexp = "^[^\\s]+$", message = "Name must not contain whitespace ")
	private String name;
	@NotNull
	@Pattern(regexp = "^[^\\s]+$", message = "Name must not contain whitespace")
	private String description;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Employee> employeeList;


	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public Department() {
		super();
	}
	public Department(int id, String name, String description, List<Employee> employeeList) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.employeeList = employeeList;
	}
	
	
}
