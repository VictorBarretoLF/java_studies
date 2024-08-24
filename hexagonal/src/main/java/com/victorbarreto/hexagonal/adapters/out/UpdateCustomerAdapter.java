package com.victorbarreto.hexagonal.adapters.out;

import com.victorbarreto.hexagonal.adapters.out.repository.CustomerEntityRepository;
import com.victorbarreto.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import com.victorbarreto.hexagonal.application.core.domain.Customer;
import com.victorbarreto.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;
    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void update(Customer customer) {
        var customerEntity = customerEntityMapper.toCustumerEntity(customer);
        customerEntityRepository.save(customerEntity);
    }

}
