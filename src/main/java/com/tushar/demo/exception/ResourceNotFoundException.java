package com.tushar.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(resourceName+" not found with "+fieldName+" : "+ fieldValue);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
