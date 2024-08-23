package com.victorbarreto.hexagonal.adapters.out;

import com.victorbarreto.hexagonal.adapters.out.repository.CustumerEntityRepository;
import com.victorbarreto.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import com.victorbarreto.hexagonal.application.core.domain.Customer;
import com.victorbarreto.hexagonal.application.ports.out.InsertCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerAdapter implements InsertCustomerOutputPort {

    @Autowired
    private CustumerEntityRepository custumerEntityRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void insert(Customer customer) {
        var custumerEntity = customerEntityMapper.toCustumerEntity(customer);
        custumerEntityRepository.save(custumerEntity);
    }

}
