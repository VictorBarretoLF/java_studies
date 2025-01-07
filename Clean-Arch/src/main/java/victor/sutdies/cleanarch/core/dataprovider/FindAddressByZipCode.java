package victor.sutdies.cleanarch.core.dataprovider;

import victor.sutdies.cleanarch.core.domain.Address;

public interface FindAddressByZipCode {

    Address find(final String zipCode);

}
