package victor.sutdies.cleanarch.core.dataprovider;

import victor.sutdies.cleanarch.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerById {

    Optional<Customer> find(final String id);
}
