package com.amazingcode.in.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.amazingcode.in.example.entity.Address;
import com.amazingcode.in.example.request.AddressRequest;
import com.amazingcode.in.example.response.AddressResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressRequest addressRequest);

    AddressResponse toAddressResponse(Address address);

    List<AddressResponse> toAddressResponseList(List<Address> addresses);
}
