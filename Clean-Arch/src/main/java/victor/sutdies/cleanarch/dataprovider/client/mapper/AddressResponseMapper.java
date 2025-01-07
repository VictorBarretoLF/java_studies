package victor.sutdies.cleanarch.dataprovider.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import victor.sutdies.cleanarch.core.domain.Address;
import victor.sutdies.cleanarch.dataprovider.client.response.AddressResponse;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    @Mapping(source = "logradouro", target = "street")
    @Mapping(source = "localidade", target = "city")
    @Mapping(source = "uf", target = "state")
    Address toAddress(AddressResponse addressResponse);

}
