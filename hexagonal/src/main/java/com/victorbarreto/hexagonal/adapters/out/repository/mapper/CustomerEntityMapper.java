package com.victorbarreto.hexagonal.adapters.out.repository.mapper;

import com.victorbarreto.hexagonal.adapters.out.repository.entity.CustomerEntity;
import com.victorbarreto.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustumerEntity(Customer customer);
    Customer toCustomer(CustomerEntity customerEntity);

}
