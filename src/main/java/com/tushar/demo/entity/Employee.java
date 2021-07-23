package com.tushar.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
	
	private static final String DATE_PATTERN = "yyyy/MM/dd";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_entity_id_seq")
	@SequenceGenerator(name = "content_entity_id_seq", sequenceName = "global_content_id_sequence", allocationSize = 1)
	@Column(name = "id")
	private int empId;
	
	@Column(name = "name")
	private String empName;
	
	@Column(name= "phone_number")
	private long empPhoneNumber;
	
	@Column(name= "email")
	private String empEmail;
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
//	@DateTimeFormat(pattern= DATE_PATTERN)
	
	@Column(name="joining_date")
	@Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-mm-yyyy")
    @DateTimeFormat(pattern="dd-mm-yyyy")
	private Date joinDate;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id")
	private Address empAddress;
	
	

}
