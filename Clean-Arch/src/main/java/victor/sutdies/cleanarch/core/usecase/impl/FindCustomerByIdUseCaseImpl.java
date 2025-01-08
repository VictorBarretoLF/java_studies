package victor.sutdies.cleanarch.core.usecase.impl;

import victor.sutdies.cleanarch.core.dataprovider.FindCustomerById;
import victor.sutdies.cleanarch.core.domain.Customer;
import victor.sutdies.cleanarch.core.usecase.FindCustomerByIdUseCase;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final FindCustomerById findCustomerById;

    public FindCustomerByIdUseCaseImpl(FindCustomerById findCustomerById) {
        this.findCustomerById = findCustomerById;
    }

    @Override
    public Customer find(String id) {
        return findCustomerById.find(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
