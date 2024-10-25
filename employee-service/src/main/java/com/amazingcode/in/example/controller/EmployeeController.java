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

import com.amazingcode.in.example.request.EmployeeRequest;
import com.amazingcode.in.example.response.EmployeeResponse;
import com.amazingcode.in.example.service.EmployeeService;

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
	public ResponseEntity<List<EmployeeResponse>> getEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("employeeId") Long employeeId){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(employeeId));
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeRequest employeeRequest){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeId, employeeRequest));
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
