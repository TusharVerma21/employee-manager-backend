package com.tushar.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Entity
@Table(name = "employee_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long addId;
	
//	@Column(name = "e")
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empAddress")
//	private Employee employee;
	
	@Column(name = "street")
//	@JsonProperty("street")
	private String street;
	
	@Column(name = "city_name")
//	@JsonProperty("cityName")
	private String cityName;
	
	@Column(name = "state_name")
//	@JsonProperty("stateName")
	private String stateName;

}
