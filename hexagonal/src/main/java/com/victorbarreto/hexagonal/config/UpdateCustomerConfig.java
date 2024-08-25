package com.victorbarreto.hexagonal.config;

import com.victorbarreto.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import com.victorbarreto.hexagonal.adapters.out.FindCustomerByIdAdapter;
import com.victorbarreto.hexagonal.adapters.out.UpdateCustomerAdapter;
import com.victorbarreto.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import com.victorbarreto.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            UpdateCustomerAdapter updateCustomerAdapter
    ) {
        return new UpdateCustomerUseCase(findCustomerByIdUseCase, findAddressByZipCodeAdapter, updateCustomerAdapter);
    }

}
