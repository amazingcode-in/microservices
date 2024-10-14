package com.amazingcode.in.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazingcode.in.example.entity.Address;
import com.amazingcode.in.example.repository.AddressRepository;
import com.amazingcode.in.example.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressByEmployeeId(Long employeeId) {
		return addressRepository.findByEmployeeId(employeeId);
	}

	@Override
	public Address updateAddress(Long addressId, Address address) {
		Address existAddress = addressRepository.findById(addressId).get();
		if(existAddress==null) {
			return null;
		}
		address.setAddressId(addressId);
		return addressRepository.save(address);
	}

	@Override
	public void deleteAddresss(Long addressId) {
		addressRepository.deleteById(addressId);
	}

}
