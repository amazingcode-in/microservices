package com.amazingcode.in.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazingcode.in.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
