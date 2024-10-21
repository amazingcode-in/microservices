package com.amazingcode.in.example.service;

import java.util.List;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.request.EmployeeRequest;
import com.amazingcode.in.example.response.EmployeeResponse;

public interface EmployeeService {
	EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

	List<Employee> getEmployees();

	EmployeeResponse getEmployee(Long employeeId);

	Employee updateEmployee(Long employeeId, Employee employee);

	void deleteEmployee(Long employeeId);
}
