package victor.sutdies.cleanarch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import victor.sutdies.cleanarch.core.usecase.impl.InsertCustomerUseCaseImpl;
import victor.sutdies.cleanarch.dataprovider.FindAddressByZipCodeImpl;
import victor.sutdies.cleanarch.dataprovider.InsertCustomerImpl;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCaseImpl insertCustomerUseCase(
            final FindAddressByZipCodeImpl findAddressByZipCode,
            final InsertCustomerImpl insertCustomer
    ) {
        return new InsertCustomerUseCaseImpl(findAddressByZipCode, insertCustomer);
    }

}
