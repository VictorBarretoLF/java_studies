package com.victorbarreto.hexagonal.adapters.out;

import com.victorbarreto.hexagonal.adapters.out.repository.CustomerEntityRepository;
import com.victorbarreto.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerByIdAdapter implements DeleteCustomerByIdOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Override
    public void delete(String id) {
        customerEntityRepository.deleteById(id);
    }

}
