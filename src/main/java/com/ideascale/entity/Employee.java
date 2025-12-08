package com.ideascale.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.ideascale.data.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567890123456789L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@ManyToOne
	private Department department;

	private Gender gender;
	@NotNull
	private String address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joiningdate;
}
