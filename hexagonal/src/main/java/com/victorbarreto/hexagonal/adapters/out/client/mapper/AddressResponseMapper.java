package com.victorbarreto.hexagonal.adapters.out.client.mapper;

import com.victorbarreto.hexagonal.adapters.out.client.response.AddressResponse;
import com.victorbarreto.hexagonal.adapters.out.client.response.ViaCepAddressResponse;
import com.victorbarreto.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);

    @Mapping(source = "logradouro", target = "street")
    @Mapping(source = "localidade", target = "city")
    @Mapping(source = "uf", target = "state")
    Address toAddress(ViaCepAddressResponse viaCepAddressResponse);

}
