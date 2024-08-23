package com.victorbarreto.hexagonal.application.ports.in;

import com.victorbarreto.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);

}
