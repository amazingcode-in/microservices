package com.amazingcode.in.example.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.amazingcode.in.example.exception.DataDuplicateException;
import com.amazingcode.in.example.external.config.AddressServiceClientConfig;
import com.amazingcode.in.example.external.request.AddressRequest;
import com.amazingcode.in.example.external.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "address-service", url = "${address.service.url}", path = "/com.amazingcode.in/api/addresses", configuration = AddressServiceClientConfig.class)
public interface AddressServiceClient {

	@PostMapping
	@CircuitBreaker(name = "addressServiceBreaker", fallbackMethod = "fallBackCreateAddress")
	AddressResponse createAddress(AddressRequest addressRequest);

	default AddressResponse fallBackCreateAddress(AddressRequest addressRequest, Exception exception) {
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setEmployeeId(addressRequest.getEmployeeId());

		if (exception instanceof DataDuplicateException) {
			addressResponse.setAddressErrorMessage(exception.getMessage());
		} else {
			addressResponse.setAddressErrorMessage(
					"Failed to save address. Address service is currently unavailable. Please try again later."+ exception.getMessage());
		}

		return addressResponse;
	}

	@GetMapping("/{employeeId}")
	@CircuitBreaker(name = "addressServiceBreaker", fallbackMethod = "fallBackGetAddress")
	AddressResponse getAddress(@PathVariable("employeeId") Long employeeId);

	default AddressResponse fallBackGetAddress(Long employeeId, Exception exception) {
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setEmployeeId(employeeId);
		addressResponse.setAddressErrorMessage(
				"Failed to get address. Address service is currently unavailable. Please try again later."
						+ exception.getMessage());
		return addressResponse;
	}

	@PutMapping("/{employeeId}")
	@CircuitBreaker(name = "addressServiceBreaker", fallbackMethod = "fallBackUpdateAddress")
	AddressResponse updateAddress(@PathVariable("employeeId") Long employeeId, AddressRequest addressRequest);

	default AddressResponse fallBackUpdateAddress(Long employeeId, AddressRequest addressRequest, Exception exception) {
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setEmployeeId(employeeId);
		addressResponse.setAddressErrorMessage(
				"Failed to update address. Address service is currently unavailable. Please try again later."
						+ exception.getMessage());
		return addressResponse;
	}

	@DeleteMapping("/{employeeId}")
	@CircuitBreaker(name = "addressServiceBreaker", fallbackMethod = "fallBackDeleteAddress")
	void deleteAddress(@PathVariable("employeeId") Long employeeId);

	default void fallBackDeleteAddress(Long employeeId, Exception exception) {

	}
}
