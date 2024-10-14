package com.amazingcode.in.example.service;

import java.util.List;

import com.amazingcode.in.example.entity.Address;

public interface AddressService {
	Address saveAddress(Address address);
	List<Address> getAddresses();
	Address getAddressByEmployeeId(Long employeeId);
	Address updateAddress(Long addressId, Address address);
	void deleteAddresss(Long addressId);
}
