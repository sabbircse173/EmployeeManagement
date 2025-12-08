package com.ideascale.apiservice;

import com.ideascale.data.EmployeeData;
import com.ideascale.data.Gender;
import com.ideascale.entity.Employee;
import com.ideascale.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EmployeeApiServiceFacadeImpl implements EmployeeApiServiceFacade{

    @Nonnull private final EmployeeService employeeService;

    @Override
    public List<EmployeeData> getEmployees() {
        return employeeService.getAllEmployee().stream()
                .map(this::buildEmployeeData)
                .collect(Collectors.toList());
    }

    private EmployeeData buildEmployeeData(Employee employee) {
        return EmployeeData.builder()
                .id(employee.getId())
                .name(employee.getName())
                .address(employee.getAddress())
                .birthdate(employee.getBirthdate())
                .joiningdate(employee.getJoiningdate())
                .departmentName(employee.getDepartment().getName())
                .gender(employee.getGender())
                .build();
    }

    @Override
    public List<EmployeeData> getEmployeesByName(String name) {
        return employeeService.getEmployeesByName(name).stream()
                .map(this::buildEmployeeData)
                .collect(Collectors.toList());
    }
}