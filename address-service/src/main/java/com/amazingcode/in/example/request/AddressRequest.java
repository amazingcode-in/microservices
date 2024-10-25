package com.amazingcode.in.example.request;

import lombok.Data;

@Data
public class AddressRequest {
    private Long employeeId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
