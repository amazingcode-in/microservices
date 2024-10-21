package com.amazingcode.in.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.external.client.AddressServiceClient;
import com.amazingcode.in.example.external.request.AddressRequest;
import com.amazingcode.in.example.external.response.Address;
import com.amazingcode.in.example.repository.EmployeeRepository;
import com.amazingcode.in.example.request.EmployeeRequest;
import com.amazingcode.in.example.response.EmployeeResponse;
import com.amazingcode.in.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final AddressServiceClient addressServiceClient;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressServiceClient addressClient) {
		this.employeeRepository = employeeRepository;
		this.addressServiceClient = addressClient;
	}

	@Override
	public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
		Employee employee = new Employee();
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setEmail(employeeRequest.getEmail());
		employee.setMobileNumber(employeeRequest.getMobileNumber());
		employee.setAge(employeeRequest.getAge());
		employee.setBloodGroup(employeeRequest.getBloodGroup());
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		AddressRequest addressRequest = employeeRequest.getAddressRequest();
		addressRequest.setEmployeeId(savedEmployee.getEmployeeId());
		Address savedAddress = addressServiceClient.createAddress(addressRequest);
		
		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployee(savedEmployee);
		employeeResponse.setAddress(savedAddress);
		return employeeResponse;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeResponse getEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();

		Address address = addressServiceClient.getAddress(employeeId);
		
		
		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployee(employee);
		employeeResponse.setAddress(address);
		return employeeResponse;
	}

	@Override
	public Employee updateEmployee(Long employeeId, Employee employee) {
		Employee existEmployee = employeeRepository.findById(employeeId).get();
		if(existEmployee==null) {
			return null;
		}
		employee.setEmployeeId(employeeId);
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
