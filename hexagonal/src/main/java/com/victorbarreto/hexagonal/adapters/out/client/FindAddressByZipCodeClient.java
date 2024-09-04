package com.victorbarreto.hexagonal.adapters.out.client;

import com.victorbarreto.hexagonal.adapters.out.client.response.AddressResponse;
import com.victorbarreto.hexagonal.adapters.out.client.response.ViaCepAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "FindAddressByZipCodeClient",
    url = "${app.client.address.url}"
)
public interface FindAddressByZipCodeClient {

    @GetMapping("/{zipCode}")
    AddressResponse find(@PathVariable("zipCode") String zipCode);

    @GetMapping("/{zipCode}/json")
    ViaCepAddressResponse findViaCep(@PathVariable("zipCode") String zipCode);

}
