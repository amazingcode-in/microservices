package com.amazingcode.in.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.external.response.AddressResponse;
import com.amazingcode.in.example.request.EmployeeRequest;
import com.amazingcode.in.example.response.EmployeeResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "firstName", source = "employee.firstName")
    @Mapping(target = "lastName", source = "employee.lastName")
    @Mapping(target = "bloodGroup", source = "employee.bloodGroup")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "mobileNumber", source = "employee.mobileNumber")
    @Mapping(target = "age", source = "employee.age")
    @Mapping(target = "addressResponse", source = "addressResponse")
    EmployeeResponse toEmployeeResponse(Employee employee, AddressResponse addressResponse);

    Employee toEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> toEmployeeResponseList(List<Employee> employees);
}
