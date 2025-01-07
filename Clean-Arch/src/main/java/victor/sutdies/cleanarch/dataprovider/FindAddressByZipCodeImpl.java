package victor.sutdies.cleanarch.dataprovider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import victor.sutdies.cleanarch.core.dataprovider.FindAddressByZipCode;
import victor.sutdies.cleanarch.core.domain.Address;
import victor.sutdies.cleanarch.dataprovider.client.FindAddressByZipCodeClient;
import victor.sutdies.cleanarch.dataprovider.client.mapper.AddressResponseMapper;

@Component
@RequiredArgsConstructor
public class FindAddressByZipCodeImpl implements FindAddressByZipCode {

    private final FindAddressByZipCodeClient findAddressByZipCodeClient;
    private final AddressResponseMapper addressResponseMapper;

    @Override
    public Address find(String zipCode) {
        var addressResponse = findAddressByZipCodeClient.find(zipCode);
        if (addressResponse.isErro()) throw new RuntimeException("CEP Inválido", null);
        return addressResponseMapper.toAddress(addressResponse);
    }

}
