package com.amazingcode.in.example.service;

import java.util.List;

import com.amazingcode.in.example.request.EmployeeRequest;
import com.amazingcode.in.example.response.EmployeeResponse;

public interface EmployeeService {
	EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

	List<EmployeeResponse> getEmployees();

	EmployeeResponse getEmployee(Long employeeId);

	EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest);

	void deleteEmployee(Long employeeId);
}
