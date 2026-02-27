package com.ideascale.facade;

import com.ideascale.data.EmployeeData;

import java.util.List;

public interface EmployeeApiServiceFacade {
    List<EmployeeData> getEmployees();

    List<EmployeeData> getEmployeesByName(String name);
}
