package victor.sutdies.cleanarch.dataprovider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.sutdies.cleanarch.core.dataprovider.FindCustomerById;
import victor.sutdies.cleanarch.core.domain.Customer;
import victor.sutdies.cleanarch.dataprovider.repository.CustomerRepository;
import victor.sutdies.cleanarch.dataprovider.repository.mapper.CustomerEntityMapper;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindCustomerByIdImpl implements FindCustomerById {

    private final CustomerRepository customerRepository;

    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public Optional<Customer> find(String id) {
        var customerEntity = customerRepository.findById(id);
        return customerEntity.map(entity -> customerEntityMapper.toCustomer(entity));
    }

}
