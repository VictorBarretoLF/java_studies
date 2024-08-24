package com.victorbarreto.hexagonal.application.ports.in;

import com.victorbarreto.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {

    void update(Customer customer, String zipCode);

}
