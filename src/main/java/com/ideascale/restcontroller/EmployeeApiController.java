package com.ideascale.restcontroller;

import com.ideascale.apiservice.EmployeeApiServiceFacade;
import com.ideascale.constants.Path;
import com.ideascale.data.EmployeeData;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Path.REST_API_PATH)
public class EmployeeApiController {
    @Nonnull private final EmployeeApiServiceFacade employeeApiServiceFacade;

    @GetMapping(Path.EMPLOYEES)
    List<EmployeeData> getEmployees() {
        return employeeApiServiceFacade.getEmployees();
    }

    @GetMapping(Path.EMPLOYEE_BY_NAME)
    public List<EmployeeData> getEmployeeByName(@PathVariable("name") String name) {
        return employeeApiServiceFacade.getEmployeesByName(name);
    }

}
