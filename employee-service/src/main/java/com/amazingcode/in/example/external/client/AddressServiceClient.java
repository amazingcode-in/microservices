package com.amazingcode.in.example.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.amazingcode.in.example.external.fallback.AddressServiceFallBack;
import com.amazingcode.in.example.external.request.AddressRequest;
import com.amazingcode.in.example.external.response.Address;

@FeignClient(name="address-service", url = "${address.service.url}", path = "/com.amazingcode.in/api/addresses"/*, fallback = AddressServiceFallBack.class*/)
public interface AddressServiceClient {
	@GetMapping("/{employeeId}")
	Address getAddress(@PathVariable("employeeId") Long employeeId);

	@PostMapping
	Address createAddress(AddressRequest addressRequest);
}
