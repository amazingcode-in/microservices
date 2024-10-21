package com.amazingcode.in.example.external.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
	private String street;
    private String city;
    private String state;
    private String zipCode;
    private Long employeeId;
}
