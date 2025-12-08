package com.ideascale.controller;

import java.util.List;

import com.ideascale.data.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideascale.entity.Department;
import com.ideascale.entity.Employee;
import com.ideascale.service.DepartmentService;
import com.ideascale.service.EmployeeService;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
@Controller
public class HomeControllers {

    @Nonnull private final DepartmentService departmentService;
    @Nonnull private final EmployeeService employeeService;

    @ModelAttribute
    public void departments(Model model) {
        List<Department> depts = departmentService.getAllDepartment();
        model.addAttribute("depts", depts);
        model.addAttribute("genders", Gender.values());
    }

    @GetMapping("/searchByAnything")
    public String searchByAnything(@RequestParam String searchTerm, Model m) {
        String trimmedTerm = searchTerm.trim().toLowerCase();
        List<Employee> empList;

        if (Gender.contains(trimmedTerm)) {
            empList = employeeService.getAllEmployeeByGender(Gender.getByName(trimmedTerm));
        } else {
            empList = employeeService.getAllEmployeeByTerm(trimmedTerm);
        }

        if (empList.isEmpty()) {
            m.addAttribute("msg",
                    "Sorry, Employees not found. Please go back to the page.");
            return "error_page";
        }
        m.addAttribute("empList", empList);
        return "emp_list";
    }

    @GetMapping({"/", "/home"})
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
        try {
            departmentService.saveDepartment(dept);
            return "redirect:/addDept";
        } catch (Exception e) {
            m.addAttribute("msg", "An unexpected error occurred. Please go back to the page.");
            return "error_page";
        }

        /// prothom Catch
//
//		catch (ConstraintViolationException ex) {
//			List<String> errors = new ArrayList<>();
//			for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//				errors.add(violation.getMessage());
//			}
//			m.addAttribute("errors", errors);  // Add errors to model for display
//			return "add_dept";  // Redirect to addDept view to display errors
//		}
    }


    @GetMapping("/editDept/{id}")
    public String editDeptById(@PathVariable int id, Model m) {
        Department dept = departmentService.getDepartmentById(id);
        if (dept != null) {
            m.addAttribute("dept", dept);
            return "edit_dept";
        }
        m.addAttribute("msg", "Sorry Department doesn't exist. Please go back to the page");
        return "error_page";
    }

    @GetMapping("/deleteDept/{id}")
    public String deleteDeptById(@PathVariable int id, Model m) {
        try {
            departmentService.deleteDepartmentById(id);
            return "redirect:/deptList";
        } catch (Exception exception) {
            m.addAttribute("msg", exception.getMessage());
            return "error_page";
        }
    }

    @PostMapping("/editDept/updateDept")
    public String updateDept(@ModelAttribute Department dept) {
        departmentService.saveDepartment(dept);
        return "redirect:/deptList";
    }


    @PostMapping("/saveEmp")
    public String saveEmp(@ModelAttribute Employee emp, Model m) {
        try {
            employeeService.saveEmployee(emp);
            return "redirect:/addEmp";
        } catch (Exception e) {
            m.addAttribute("msg", "An unexpected error occurred. Please go back to the page.");
            return "error_page";
        }
    }

    @GetMapping("/editEmp/{id}")
    public String editEmpById(@PathVariable int id, Model m) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            m.addAttribute("emp", emp);
            return "edit_emp";
        }
        m.addAttribute("msg", "Sorry Employee doesn't exist. Please go back to the page");
        return "error_page";
    }

    @PostMapping("/editEmp/updateEmp")
    public String updateEmp(@ModelAttribute Employee emp) {
        employeeService.saveEmployee(emp);
        return "redirect:/empList";
    }

    @GetMapping("/deleteEmp/{id}")
    public String deleteEmpById(@PathVariable int id, Model m) {
        if (employeeService.deleteEmployeeById(id)) {
            return "redirect:/empList";
        }
        m.addAttribute("msg", "Already Deleted. Please go back to the page");
        return "error_page";
    }
}
