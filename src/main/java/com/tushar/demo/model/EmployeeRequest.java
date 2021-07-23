package com.tushar.demo.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.tushar.demo.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
	
	@NotNull(message = "Name can't be empty!")
	@Size(min = 2)
	private String empName;

	@NotNull(message = "Phone Number can't be empty!")
	private long empPhoneNumber;

	@NotNull(message = "Email can't be empty!")
	@Email
	private String empEmail;
	
	private Date joinDate;
	
	/*
	private String username;
	
	private String password;
	*/

//	private String empAddress;
	
	private Address empAddress;

}
