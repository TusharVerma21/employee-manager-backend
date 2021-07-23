package com.tushar.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.tushar.demo.UserPrincipal;
import com.tushar.demo.dto.EmployeeResponse;
import com.tushar.demo.entity.Employee;
import com.tushar.demo.exception.ResourceNotFoundException;
import com.tushar.demo.model.EmployeeRequest;
import com.tushar.demo.repository.EmployeeRepository;

import antlr.StringUtils;

@Service
//public class EmployeeService implements UserDetailsService{
public class EmployeeService {

	
	@Autowired
	EmployeeRepository repo;
	@Autowired
	ModelMapper modelMapper;
	
	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee emp = repo.findByUsername(username);
		
		if (emp==null)
			throw new UsernameNotFoundException("User not found!!!");
		
		return new UserPrincipal(emp);
	}
	*/
	
	@Transactional
	public List<EmployeeResponse> getEmployees(){
		
		Iterable<Employee> employees = repo.findAll();
		List<EmployeeResponse> data = modelMapper.map(employees, new TypeToken<List<EmployeeResponse>>() {}.getType());

		return data;
	}
	
	public List<EmployeeResponse> getEmployees3(String name, Date joinDate, Pageable pageable){
		
		List<Employee> employees = repo.findAll((Specification<Employee>) (root, cq, cb) ->{
			
			Predicate p = cb.conjunction();
			
			if (!org.springframework.util.StringUtils.isEmpty(name)) {
				p = cb.and(p, cb.like(root.get("empName"), "%" + name + "%"));
			}
			
			if (Objects.nonNull(joinDate)) {
				p = cb.and(p, cb.like(root.get("joinDate"), "%" + joinDate + "%"));
            }
			
			return p;
			
		} , pageable).getContent();
		
		List<EmployeeResponse> data = modelMapper.map(employees, new TypeToken<List<EmployeeResponse>>() {}.getType());

		return data;
		
	}
	
	public List<EmployeeResponse> getEmployees1(int page){
		Pageable pePageable = PageRequest.of(page-1, 2);
		Page<Employee> employees = repo.findAll(pePageable);
		Page<EmployeeResponse> data = modelMapper.map(employees, new TypeToken<Page<EmployeeResponse>>() {}.getType());
		
		return data.getContent();
	}
	
	public List<EmployeeResponse> findPaginated(int page, int size){
		
		Pageable paging = PageRequest.of(page-1, size);
		Page<Employee> employees = repo.findAll(paging);
		Page<EmployeeResponse> data = modelMapper.map(employees, new TypeToken<Page<EmployeeResponse>>() {}.getType());
		return data.getContent();
	}
	
	
	public EmployeeResponse getEmployee(int eId) {
		
		/*
		Optional<Employee> e = repo.findById(eId);
		
		if(e.isPresent()) {
			return modelMapper.map(e.get(), EmployeeResponse.class);
		}
		else {
			throw new ResourceNotFoundException("Employee", "Id", eId);
		}
		*/
		
		// lambda expression
		return modelMapper.map(repo.findById(eId).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", eId)), EmployeeResponse.class);
	}
	
	public EmployeeResponse postEmployee(EmployeeRequest newEmployee) {
		
		Employee e = repo.save(modelMapper.map(newEmployee, Employee.class));
		
		return modelMapper.map(e, EmployeeResponse.class);
	}
	
	public EmployeeResponse updateEmployee(EmployeeRequest employee, int eId) {
		
		Employee existingEmployee = repo.findById(eId).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", eId));
		
		existingEmployee.setEmpName(employee.getEmpName());
		existingEmployee.setEmpPhoneNumber(employee.getEmpPhoneNumber());
		existingEmployee.setEmpEmail(employee.getEmpEmail());
		existingEmployee.setEmpAddress(employee.getEmpAddress());
		
		Employee e = repo.save(modelMapper.map(existingEmployee, Employee.class));
		
		return modelMapper.map(e, EmployeeResponse.class);
	}
	
	public void deleteEmployee(int eId) {

		repo.findById(eId).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", eId));
		
		repo.deleteById(eId);
	}

}
