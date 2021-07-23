package com.tushar.demo.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tushar.demo.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
	
	private int empId;
	
	private String empName;

	private long empPhoneNumber;

	private String empEmail;
	
	private Date joinDate;
	
//	private String empAddress;
	
	private Address empAddress;

}
