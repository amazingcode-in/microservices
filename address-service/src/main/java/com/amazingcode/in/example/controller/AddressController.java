package com.amazingcode.in.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazingcode.in.example.request.AddressRequest;
import com.amazingcode.in.example.response.AddressResponse;
import com.amazingcode.in.example.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddress(addressRequest));
	}
	
	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAddresses() {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddresses());
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<AddressResponse> getAddress(@PathVariable("employeeId") Long employeeId){
		return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressByEmployeeId(employeeId));
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable("employeeId") Long employeeId, @RequestBody AddressRequest addressRequest){
		return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(employeeId, addressRequest));
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("employeeId") Long employeeId){
		addressService.deleteAddresss(employeeId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
