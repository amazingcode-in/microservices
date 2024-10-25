package com.amazingcode.in.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.exception.DataDuplicateException;
import com.amazingcode.in.example.external.client.AddressServiceClient;
import com.amazingcode.in.example.external.request.AddressRequest;
import com.amazingcode.in.example.external.response.AddressResponse;
import com.amazingcode.in.example.mapper.EmployeeMapper;
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

		Employee toSaveEmployee = EmployeeMapper.INSTANCE.toEmployee(employeeRequest);

		Employee savedEmployee = employeeRepository.save(toSaveEmployee);

		AddressRequest addressRequest = employeeRequest.getAddressRequest();
		addressRequest.setEmployeeId(savedEmployee.getEmployeeId());

		try{
			AddressResponse savedAddress = addressServiceClient.createAddress(addressRequest);
			return EmployeeMapper.INSTANCE.toEmployeeResponse(savedEmployee, savedAddress);
		}catch(DataDuplicateException exception){
			AddressResponse addressResponse = new AddressResponse();
			addressResponse.setAddressErrorMessage(exception.getMessage());
			return EmployeeMapper.INSTANCE.toEmployeeResponse(savedEmployee, addressResponse);
			
		}
	}

	@Override
	public List<EmployeeResponse> getEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeResponse> employeeResponses = new ArrayList<>();

		for (Employee employee : employees) {

			AddressResponse addressResponse = addressServiceClient.getAddress(employee.getEmployeeId());

			employeeResponses.add(EmployeeMapper.INSTANCE.toEmployeeResponse(employee,addressResponse));
		}

		return employeeResponses;
	}

	@Override
	public EmployeeResponse getEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
		AddressResponse addressResponse = addressServiceClient.getAddress(employeeId);

		return EmployeeMapper.INSTANCE.toEmployeeResponse(employee, addressResponse);
	}

	@Override
	public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
		Employee existEmployee = employeeRepository.findById(employeeId).get();
		if (existEmployee == null) {
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setEmployeeErrorMessage("Employee with given id: "+employeeId+" not exist.");
		}
		Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeeRequest);
		employee.setEmployeeId(employeeId);
		Employee updatedEmployee = employeeRepository.save(employee);

		AddressRequest addressRequest = employeeRequest.getAddressRequest();
		AddressResponse updatedAddress = addressServiceClient.updateAddress(employeeId, addressRequest);

		return EmployeeMapper.INSTANCE.toEmployeeResponse(updatedEmployee, updatedAddress);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
		addressServiceClient.deleteAddress(employeeId);
	}

}
