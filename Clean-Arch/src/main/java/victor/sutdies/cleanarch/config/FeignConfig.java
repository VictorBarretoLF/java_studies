package victor.sutdies.cleanarch.config;

import org.springframework.context.annotation.Bean;
import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
