package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.becoder.entity.Department;
import com.becoder.entity.Employee;
import com.becoder.service.DepartmentService;
import com.becoder.service.EmployeeService;

@Controller
public class HomeControllers {
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public void depts(Model m) {
		List<Department> depts = departmentService.getAllDepartment();
		m.addAttribute("depts", depts);
	}
	
	@GetMapping("/searchByAnything")
	public String searchByAnything(@RequestParam String search_str, Model m) {
		int lower_bound = 0, upper_bound = search_str.length() - 1;
		if(search_str.charAt(0) == ' ') {
			while(search_str.charAt(lower_bound) == ' ') {
				lower_bound++;
			}
		}
		
		if(search_str.charAt(upper_bound) == ' ') {
			while(search_str.charAt(upper_bound) == ' ') {
				upper_bound--;
			}
		}
		String optimalStr = search_str.substring(lower_bound, upper_bound + 1);
		List<Employee> empList = employeeService.getAllEmployeeBySearch(optimalStr);
		
		if(empList.isEmpty()) {
			m.addAttribute("msg", 
					"Sorry, Employees not found.");
			return "error_page";
		}
		m.addAttribute("empList", empList);
		return "search_page";
	}
	
	@GetMapping("/")
	public String Home() {
		return "home";
	}
	
	@GetMapping("/addDept")
	public String addDept() {
		return "add_dept";
	}
	
	@GetMapping("/addEmp")
	public String addEmp(Model m) {
		return "add_emp";
	}
	
	@GetMapping("/empList")
	public String empList(Model m) {
		List<Employee> empList = employeeService.getAllEmployee();
		m.addAttribute("empList", empList);
		return "emp_list";
	}
	
	@GetMapping("/deptList")
	public String deptList(Model m) {
		List<Department> deptList = departmentService.getAllDepartment();
		m.addAttribute("deptList", deptList);
		return "dept_list";
	}
	
	@PostMapping("/saveDept")
	public String saveDept(@ModelAttribute Department dept, Model m) {
		departmentService.saveDepartment(dept);
		
		return "redirect:/addDept";
	}
	
	
	@GetMapping("/editDept/{id}")
	public String editDeptById(@PathVariable int id, Model m) {
		Department dept = departmentService.getDepartmentById(id);
		if(dept != null) {
			m.addAttribute("dept", dept);
			return "edit_dept";
		}
		m.addAttribute("msg", "Sorry Department doesn't exist");
		return "error_page";
	}
	
	@GetMapping("/deleteDept/{id}")
	public String deleteDeptById(@PathVariable int id, Model m) {
		if(departmentService.deleteDepartmentById(id)) {
			return "redirect:/deptList";
		}
		m.addAttribute("msg", "Already Deleted");
		return "error_page";
	}
	
	@PostMapping("/editDept/updateDept")
	public String updateDept(@ModelAttribute Department dept) {
		departmentService.saveDepartment(dept);
		return "redirect:/deptList";
	}
	
	
	
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, Model m) {
		employeeService.saveEmployee(emp);
		return "redirect:/addEmp";
	}
	
	@GetMapping("/editEmp/{id}")
	public String editEmpById(@PathVariable int id, Model m) {
		Employee emp = employeeService.getEmployeeById(id);
		if(emp != null) {
			m.addAttribute("emp", emp);
			return "edit_emp";
		}
		m.addAttribute("msg", "Sorry Employee doesn't exist");
		return "error_page";
	}
	
	@PostMapping("/editEmp/updateEmp")
	public String updateEmp(@ModelAttribute Employee emp) {
		employeeService.saveEmployee(emp);
		return "redirect:/empList";
	}
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmpById(@PathVariable int id, Model m) {
		if(employeeService.deleteEmployeeById(id)) {
			return "redirect:/empList";
		}
		m.addAttribute("msg", "Already Deleted");
		return "error_page";
	}
}
