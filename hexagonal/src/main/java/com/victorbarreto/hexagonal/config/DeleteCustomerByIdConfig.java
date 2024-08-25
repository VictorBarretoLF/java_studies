package com.victorbarreto.hexagonal.config;

import com.victorbarreto.hexagonal.adapters.out.DeleteCustomerByIdAdapter;
import com.victorbarreto.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import com.victorbarreto.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {

    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter
    ) {
        return new DeleteCustomerByIdUseCase(findCustomerByIdUseCase, deleteCustomerByIdAdapter);
    }

}
