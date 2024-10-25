package com.amazingcode.in.example.response;

import com.amazingcode.in.example.external.response.AddressResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String bloodGroup;
	private String email;
	private String  mobileNumber;
	private Long age;

	private String employeeErrorMessage;

	private AddressResponse addressResponse;
}
