package com.amazingcode.in.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazingcode.in.example.entity.Address;
import com.amazingcode.in.example.exception.DataDuplicateException;
import com.amazingcode.in.example.mapper.AddressMapper;
import com.amazingcode.in.example.repository.AddressRepository;
import com.amazingcode.in.example.request.AddressRequest;
import com.amazingcode.in.example.response.AddressResponse;
import com.amazingcode.in.example.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public AddressResponse saveAddress(AddressRequest addressRequest) {
		boolean isPresentAddress = addressRepository.existsByEmployeeId(addressRequest.getEmployeeId());
		if(isPresentAddress){
			throw new DataDuplicateException("Address is already present for employee id: "+addressRequest.getEmployeeId());
		}
		Address address = AddressMapper.INSTANCE.toAddress(addressRequest);
		return AddressMapper.INSTANCE.toAddressResponse(addressRepository.save(address));
	}

	@Override
	public List<AddressResponse> getAddresses() {
		return AddressMapper.INSTANCE.toAddressResponseList(addressRepository.findAll());
	}

	@Override
	public AddressResponse getAddressByEmployeeId(Long employeeId) {
		return AddressMapper.INSTANCE.toAddressResponse(addressRepository.findByEmployeeId(employeeId));
	}

	@Override
	public AddressResponse updateAddress(Long employeeId, AddressRequest addressRequest) {
		Address existAddress = addressRepository.findByEmployeeId(employeeId);
		if(existAddress==null) {
			AddressResponse addressResponse = new AddressResponse();
			addressResponse.setAddressErrorMessage("Address with employee id: "+employeeId+" not present.");
			return addressResponse;
		}
		Address toUpdateAddress = AddressMapper.INSTANCE.toAddress(addressRequest);
		toUpdateAddress.setAddressId(existAddress.getAddressId());
		toUpdateAddress.setEmployeeId(existAddress.getEmployeeId());
		return AddressMapper.INSTANCE.toAddressResponse(addressRepository.save(toUpdateAddress));
	}

	@Override
	public void deleteAddresss(Long employeeId) {
		Address existAddress = addressRepository.findByEmployeeId(employeeId);
		addressRepository.deleteById(existAddress.getAddressId());
	}

}
