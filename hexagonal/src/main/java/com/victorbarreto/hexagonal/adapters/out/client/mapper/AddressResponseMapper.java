package com.victorbarreto.hexagonal.adapters.out.client.mapper;

import com.victorbarreto.hexagonal.adapters.out.client.response.AddressResponse;
import com.victorbarreto.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);

}
