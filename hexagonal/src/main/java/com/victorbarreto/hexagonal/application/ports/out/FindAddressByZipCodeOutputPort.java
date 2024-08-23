package com.victorbarreto.hexagonal.application.ports.out;

import com.victorbarreto.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);
}
