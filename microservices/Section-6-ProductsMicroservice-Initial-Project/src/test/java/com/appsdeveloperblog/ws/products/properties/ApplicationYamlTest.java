package com.appsdeveloperblog.ws.products.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ApplicationYamlTest {

    @Value("${test.property}")
    private String testProperty;

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private Boolean enableIdempotence;

    @Test
    public void testApplicationYamlLoaded() {
        // Assert that the property has been loaded correctly from application.yaml
        Assertions.assertEquals("Configuration Loaded", testProperty);
    }

    @Test
    public void testEnableIdempotence() {
        // Assert that the property has been loaded correctly from application.yaml
        Assertions.assertEquals(true, enableIdempotence);
    }

}
