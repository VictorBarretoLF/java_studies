package com.victorbarreto.hexagonal.application.ports.in;

import com.victorbarreto.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);

}
