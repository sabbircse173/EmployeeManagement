package com.ideascale.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


// Didn't use lombok intentionally here.
@Table(name = "department")
@Entity
public class Department implements Serializable {

    @Serial
	private static final long serialVersionUID = 8748619341654579468L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
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
