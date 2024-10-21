package com.amazingcode.in.example.external.fallback;

import org.springframework.stereotype.Component;

import com.amazingcode.in.example.external.client.AddressServiceClient;
import com.amazingcode.in.example.external.request.AddressRequest;
import com.amazingcode.in.example.external.response.Address;

@Component
public class AddressServiceFallBack /*implements AddressServiceClient*/ {

//	@Override
	public Address getAddress(Long employeeId) {
		// TODO Auto-generated method stub
		return new Address(0L,"N/A","N/A","N/A","N/A",employeeId);
	}

//	@Override
	public Address createAddress(AddressRequest addressRequest) {
		// TODO Auto-generated method stub
		return new Address(0L,"N/A","N/A","N/A","N/A",addressRequest.getEmployeeId());
	}

}
