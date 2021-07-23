package com.tushar.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.demo.dto.EmployeeResponse;
import com.tushar.demo.entity.Employee;
import com.tushar.demo.model.EmployeeRequest;
import com.tushar.demo.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@RequestMapping("/employees")
public class EmployeeController {
	
	private static final String DATE_PATTERN = "yyyy/MM/dd";
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employees")
	public List<EmployeeResponse> getEmployees(){
		return service.getEmployees();
	}
	
	/*
	@GetMapping("/employees")
	public List<EmployeeResponse> getEmployees(@RequestParam(required = false) String name,@DateTimeFormat(pattern = DATE_PATTERN) Date joinDate, Pageable pageable){
		return service.getEmployees3(name, joinDate, pageable);
	}
	*/
	
	@GetMapping("/employees/{page}")
	public List<EmployeeResponse> getEmployees1(@PathVariable int page){
		return service.getEmployees1(page);
	}
	
	@GetMapping("/employees/{page}/{size}")
	public List<EmployeeResponse> getPaginatedEmployees(@PathVariable int page, @PathVariable int size){		
		return service.findPaginated(page, size);
	}
	
	@GetMapping("/employee/{eId}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Integer eId) {
		return new ResponseEntity<EmployeeResponse>(service.getEmployee(eId), HttpStatus.OK) ;
	}
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeResponse> postEmployee(@Valid @RequestBody EmployeeRequest newEmployee) {
		return new ResponseEntity<EmployeeResponse>(service.postEmployee(newEmployee), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("id") int eId, @RequestBody EmployeeRequest employee){
		return new ResponseEntity<EmployeeResponse>(service.updateEmployee(employee, eId), HttpStatus.OK);
	}
	
	/*
	@DeleteMapping("/employee/{eId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer eId) {
		service.deleteEmployee(eId);
		
		return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
	}
	*/
	
	@DeleteMapping("/employee/{eId}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer eId) {
		service.deleteEmployee(eId);
		
		Map<String, Boolean> deleteResponse = new HashMap<>();
		
		deleteResponse.put("deleted", Boolean.TRUE);
				
		return ResponseEntity.ok(deleteResponse);
	}

}
