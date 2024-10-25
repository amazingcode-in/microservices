package com.amazingcode.in.example.request;

import com.amazingcode.in.example.external.request.AddressRequest;

import lombok.Data;

@Data
public class EmployeeRequest {
	private String firstName;
	private String lastName;
	private String bloodGroup;
	private String email;
	private String  mobileNumber;
	private Long age;
	
	private AddressRequest addressRequest;
}
