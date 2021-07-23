package com.tushar.demo.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String details;
	

}
