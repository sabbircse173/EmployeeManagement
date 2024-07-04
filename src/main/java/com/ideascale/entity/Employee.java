package com.ideascale.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@ManyToOne
	private Department department;
	private String gender;
	@NotNull
	private String address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joiningdate;
	
	
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Date getJoiningdate() {
		return joiningdate;
	}
	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}
	
	
	
	public Employee() {
		super();
	}
	public Employee(int id, String name, Department department, String gender, String address, Date birthdate,
					Date joiningdate) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.gender = gender;
		this.address = address;
		this.birthdate = birthdate;
		this.joiningdate = joiningdate;
	}
	
	
}
