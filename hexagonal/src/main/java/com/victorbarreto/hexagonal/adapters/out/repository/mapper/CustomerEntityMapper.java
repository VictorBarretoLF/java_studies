package com.victorbarreto.hexagonal.adapters.out.repository.mapper;

import com.victorbarreto.hexagonal.adapters.out.repository.entity.CustumerEntity;
import com.victorbarreto.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustumerEntity toCustumerEntity(Customer customer);

}
