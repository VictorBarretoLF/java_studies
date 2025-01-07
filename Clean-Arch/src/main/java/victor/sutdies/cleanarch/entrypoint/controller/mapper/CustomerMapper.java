package victor.sutdies.cleanarch.entrypoint.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import victor.sutdies.cleanarch.core.domain.Customer;
import victor.sutdies.cleanarch.entrypoint.controller.request.CustomerRequest;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "isValidCpf", ignore = true)
    Customer toCustomer(CustomerRequest customerRequest);

}
