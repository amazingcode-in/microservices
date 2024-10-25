package com.amazingcode.in.example.service;

import java.util.List;

import com.amazingcode.in.example.request.AddressRequest;
import com.amazingcode.in.example.response.AddressResponse;

public interface AddressService {
	AddressResponse saveAddress(AddressRequest addressRequest);
	List<AddressResponse> getAddresses();
	AddressResponse getAddressByEmployeeId(Long employeeId);
	AddressResponse updateAddress(Long employeeId, AddressRequest addressRequest);
	void deleteAddresss(Long employeeId);
}
