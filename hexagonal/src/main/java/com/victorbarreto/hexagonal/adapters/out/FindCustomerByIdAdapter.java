package com.victorbarreto.hexagonal.adapters.out;

import com.victorbarreto.hexagonal.adapters.out.repository.CustomerEntityRepository;
import com.victorbarreto.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import com.victorbarreto.hexagonal.application.core.domain.Customer;
import com.victorbarreto.hexagonal.application.ports.out.FindCustomerByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindCustomerByIdAdapter implements FindCustomerByIdOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;
    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public Optional<Customer> find(String id) {
        var customerEntity = customerEntityRepository.findById(id);
        return customerEntity.map(entity -> customerEntityMapper.toCustomer(entity));
    }

}
