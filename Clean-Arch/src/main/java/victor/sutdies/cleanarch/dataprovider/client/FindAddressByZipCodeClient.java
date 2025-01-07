package victor.sutdies.cleanarch.dataprovider.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import victor.sutdies.cleanarch.config.FeignConfig;
import victor.sutdies.cleanarch.dataprovider.client.response.AddressResponse;

@FeignClient(
        name = "FindAddressByZipCodeClient",
        url = "${viacep.url}",
        configuration = FeignConfig.class
)
public interface FindAddressByZipCodeClient {

    @GetMapping("/{zipCode}/json")
    AddressResponse find(@PathVariable("zipCode") final String zipCode);

}
