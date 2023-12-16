package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.becoder.entity.Department;
import com.becoder.entity.Employee;
import com.becoder.service.DepartmentService;
import com.becoder.service.EmployeeService;

@Controller
public class HomeControllers {
	@Autowired
	private DepartmentService deptServ;
	
	@Autowired
	private EmployeeService empServ;
	
	@ModelAttribute
	public void depts(Model m) {
		List<Department> depts = deptServ.getAllDepartment();
		m.addAttribute("depts", depts);
	}
	
	@GetMapping("/")
	public String Home() {
		return "home.html";
	}
	@GetMapping("/addDept")
	public String addDept() {
		return "add_dept.html";
	}
	
	@GetMapping("/addEmp")
	public String addEmp(Model m) {
		List<Department> depts = deptServ.getAllDepartment();
		m.addAttribute("depts", depts);
		return "add_emp.html";
	}
	
	@GetMapping("/empList")
	public String empList(Model m) {
		List<Employee> empList = empServ.getAllEmployee();
		m.addAttribute("empList", empList);
		return "emp_list.html";
	}
	
	@GetMapping("/deptList")
	public String deptList(Model m) {
		List<Department> deptList = deptServ.getAllDepartment();
		m.addAttribute("deptList", deptList);
		return "dept_list.html";
	}
	
	@PostMapping("/saveDept")
	public String saveDept(@ModelAttribute Department dept, Model m) {
		deptServ.SaveDepartment(dept);
		m.addAttribute("msg", "Successfully Added");
		return "redirect:/deptList";
	}
	
	
	@GetMapping("/editDept/{id}")
	public String editDeptById(@PathVariable int id, Model m) {
		Department dept = deptServ.getDepartmentById(id);
		m.addAttribute("dept", dept);
		return "edit_dept.html";
	}
	
	@GetMapping("/deleteDept/{id}")
	public String deleteDeptById(@PathVariable int id, Model m) {
		deptServ.DeleteDeptById(id);
		return "redirect:/deptList";
	}
	
	@PostMapping("/editDept/updateDept")
	public String updateDept(@ModelAttribute Department dept) {
		deptServ.SaveDepartment(dept);
		return "redirect:/deptList";
	}
	
	
	
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, Model m) {
		empServ.saveEmployee(emp);
		m.addAttribute("msg", "Successfully Added");
		return "redirect:/empList";
	}
	
	@GetMapping("/editEmp/{id}")
	public String editEmpById(@PathVariable int id, Model m) {
		Employee emp = empServ.getEmployeeById(id);
		m.addAttribute("emp", emp);
		return "edit_emp.html";
	}
	
	@PostMapping("/editEmp/updateEmp")
	public String updateEmp(@ModelAttribute Employee emp) {
		empServ.saveEmployee(emp);
		return "redirect:/empList";
	}
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmpById(@PathVariable int id, Model m) {
		empServ.deleteEmployeeById(id);
		return "redirect:/empList";
	}
}
