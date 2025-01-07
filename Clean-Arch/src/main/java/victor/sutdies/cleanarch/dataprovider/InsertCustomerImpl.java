package victor.sutdies.cleanarch.dataprovider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.sutdies.cleanarch.core.dataprovider.InsertCustomer;
import victor.sutdies.cleanarch.core.domain.Customer;
import victor.sutdies.cleanarch.dataprovider.repository.CustomerRepository;
import victor.sutdies.cleanarch.dataprovider.repository.mapper.CustomerEntityMapper;

@Component
@RequiredArgsConstructor
public class InsertCustomerImpl implements InsertCustomer {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public void insert(Customer customer) {
        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }

}
