package victor.sutdies.cleanarch.core.usecase;

import victor.sutdies.cleanarch.core.domain.Customer;

public interface FindCustomerByIdUseCase {

    Customer find(String id);

}
