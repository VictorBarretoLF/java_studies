package com.victorbarreto.hexagonal.application.ports.out;

import com.victorbarreto.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerOutputPort {

    void update(Customer customer);

}
