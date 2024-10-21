package com.amazingcode.in.example.response;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.external.response.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
	private Employee employee;
	private Address address;
	private String errorMessage;
}
