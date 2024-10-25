package com.amazingcode.in.example.external.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
	private Long addressId;
    private Long employeeId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    private String addressErrorMessage;
}
