package com.victorbarreto.hexagonal.application.ports.out;

import com.victorbarreto.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {

    void insert(Customer customer);
}
