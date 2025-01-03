package com.victorbarreto.hexagonal.application.ports.out;

import com.victorbarreto.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);

}
