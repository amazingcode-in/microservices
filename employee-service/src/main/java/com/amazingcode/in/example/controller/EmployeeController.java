package com.amazingcode.in.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.external.response.Address;
import com.amazingcode.in.example.request.EmployeeRequest;
import com.amazingcode.in.example.response.EmployeeResponse;
import com.amazingcode.in.example.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeRequest));
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
	}
	
	@GetMapping("/{employeeId}")
	@CircuitBreaker(name = "addressServiceBreaker", fallbackMethod = "addressServiceFallBack")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("employeeId") Long employeeId){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(employeeId));
	}
	
	public ResponseEntity<EmployeeResponse> addressServiceFallBack(Long employeeId, Exception ex){
		EmployeeResponse employeeResponse = new EmployeeResponse();
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employeeResponse.setEmployee(employee);
		employeeResponse.setAddress(new Address());
		employeeResponse.setErrorMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employeeResponse);
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeId, employee));
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
