package com.becoder.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String department;
	private String gender;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
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
	public Employee(int id, String name, String department, String gender, String address, Date birthdate,
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
