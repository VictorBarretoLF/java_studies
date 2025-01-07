package victor.sutdies.cleanarch.core.usecase.impl;

import victor.sutdies.cleanarch.core.dataprovider.FindAddressByZipCode;
import victor.sutdies.cleanarch.core.dataprovider.InsertCustomer;
import victor.sutdies.cleanarch.core.domain.Customer;
import victor.sutdies.cleanarch.core.usecase.InsertCustomerUseCase;

public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private final FindAddressByZipCode findAddressByZipCode;

    private final InsertCustomer insertCustomer;

    public InsertCustomerUseCaseImpl(
            FindAddressByZipCode findAddressByZipCode,
            InsertCustomer insertCustomer
    ) {
        this.findAddressByZipCode = findAddressByZipCode;
        this.insertCustomer = insertCustomer;
    }

    @Override
    public void insert(Customer customer, String zipCode) {
        var address = findAddressByZipCode.find(zipCode);
        customer.setAddress(address);
        insertCustomer.insert(customer);
    }

}
