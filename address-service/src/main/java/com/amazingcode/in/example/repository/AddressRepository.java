package com.amazingcode.in.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazingcode.in.example.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByEmployeeId(Long employeeId);

}
