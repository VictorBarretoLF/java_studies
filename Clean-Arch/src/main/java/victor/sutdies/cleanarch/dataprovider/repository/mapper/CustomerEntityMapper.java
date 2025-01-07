package victor.sutdies.cleanarch.dataprovider.repository.mapper;

import org.mapstruct.Mapper;
import victor.sutdies.cleanarch.core.domain.Customer;
import victor.sutdies.cleanarch.dataprovider.repository.entity.CustomerEntity;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);

}
