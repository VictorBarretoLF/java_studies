package victor.sutdies.cleanarch.core.usecase;

import victor.sutdies.cleanarch.core.domain.Customer;

public interface InsertCustomerUseCase {

    void insert(Customer customer, String zipCode);

}
