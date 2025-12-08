package com.ideascale.repository;

import com.ideascale.data.Gender;
import com.ideascale.entity.Employee;
import com.ideascale.entity.QEmployee;
import com.ideascale.entity.QDepartment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepositoryImpl extends QuerydslRepositorySupport implements EmployeeRepositoryCustom {

    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        QEmployee employee = new QEmployee("employee");
        
        return from(employee)
                .where(employee.name.equalsIgnoreCase(name))
                .fetch();
    }

    @Override
    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        QEmployee employee = new QEmployee("employee");
        QDepartment department = new QDepartment("department");
        
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(departmentName)) {
            builder.and(department.name.containsIgnoreCase(departmentName));
        }
        
        return from(employee)
                .join(employee.department, department)
                .where(builder)
                .select(employee)
                .fetch();
    }

    @Override
    public List<Employee> getEmployeesByGenderAndDepartment(Gender gender, String departmentName) {
        QEmployee employee = new QEmployee("employee");
        QDepartment department = new QDepartment("department");

        return from(employee)
                .join(department)
                .on(department.id.eq(employee.department.id))
                .where(employee.gender.eq(gender)
                        .and(department.name.containsIgnoreCase(departmentName)))
                .select(employee)
                .fetch();

    }

    @Override
    public List<Employee> getEmployeesJoinedAfter(Date joiningDate) {
        QEmployee employee = new QEmployee("employee");
        
        return from(employee)
                .where(employee.joiningdate.after(joiningDate))
                .orderBy(employee.joiningdate.desc())
                .select(employee)
                .fetch();
    }

    @Override
    public List<Employee> getEmployeesByAgeRange(Date fromBirthDate, Date toBirthDate) {
        QEmployee employee = new QEmployee("employee");
        
        return from(employee)
                .where(employee.birthdate.between(fromBirthDate, toBirthDate))
                .orderBy(employee.birthdate.desc())
                .select(employee)
                .fetch();
    }

    @Override
    public Page<Employee> searchEmployees(String searchTerm, Pageable pageable) {
        QEmployee employee = new QEmployee("employee");
        QDepartment department = new QDepartment("department");
        
        BooleanBuilder builder = new BooleanBuilder();
        
        if (StringUtils.hasText(searchTerm)) {
            builder.or(employee.name.containsIgnoreCase(searchTerm))
                   .or(employee.address.containsIgnoreCase(searchTerm))
                   .or(department.name.containsIgnoreCase(searchTerm));
        }
        
        JPQLQuery<Employee> query = from(employee)
                .leftJoin(employee.department, department)
                .where(builder)
                .select(employee);
        
        return QueryDslPageUtils.createPage(Objects.requireNonNull(getQuerydsl()), query, pageable);
    }

    @Override
    public long countEmployeesByDepartment(String departmentName) {
        QEmployee employee = new QEmployee("employee");
        QDepartment department = new QDepartment("department");
        
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(departmentName)) {
            builder.and(department.name.containsIgnoreCase(departmentName));
        }
        
        return from(employee)
                .join(employee.department, department)
                .where(builder)
                .fetchCount();
    }

    @Override
    public List<Employee> getAllEmployeesByTerm(String term) {
        QEmployee employee = new QEmployee("emp");
        return from(employee)
                .where(employee.name.like("%" + term + "%")
                        .or(employee.address.like("%" + term + "%"))
                        .or(employee.department.name.like("%" + term + "%")))
                .fetch();
    }
}