package com.ideascale.data;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeData {
    private int id;
    private String name;
    private String departmentName;
    private Gender gender;
    private String address;
    private Date birthdate;
    private Date joiningdate;
}
