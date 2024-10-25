package com.amazingcode.in.example.response;

import lombok.Data;

@Data
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
