package com.victorbarreto.hexagonal.config;

import com.victorbarreto.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import com.victorbarreto.hexagonal.adapters.out.InsertCustomerAdapter;
import com.victorbarreto.hexagonal.adapters.out.SendCpfValidationAdapter;
import com.victorbarreto.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapter findAddressAdapter,
            InsertCustomerAdapter insertAdapter,
            SendCpfValidationAdapter validationAdapter
    ) {
        return new InsertCustomerUseCase(findAddressAdapter, insertAdapter, validationAdapter);
    }

}
