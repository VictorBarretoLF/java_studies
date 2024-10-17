package com.appsdeveloperblog.ws.products.service.impl;

import com.appsdeveloperblog.ws.products.dto.CreateProductDto;
import com.appsdeveloperblog.ws.products.event.ProductCreatedEvent;
import com.appsdeveloperblog.ws.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProductAsync(CreateProductDto createProductDto) {
        String productId = UUID.randomUUID().toString();

        // TODO: Save product to the database before publishing event

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId,
                createProductDto.getTitle(),
                createProductDto.getPrice(),
                createProductDto.getQuantity()
        );

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future
                = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);

        future.whenComplete((result, exception) -> {
            if (exception == null) {
                logger.info("***** Successfully published ProductCreatedEvent for product ID: {}", productId);
            } else {
                logger.error("***** Failed to publish ProductCreatedEvent for product ID: {}. Exception: {}", productId, exception.getMessage(), exception);
            }
        });

        // This is a blocking operation to wait for the future to complete so that it is similar to a synchronous operation
        // future.join();

        logger.info("***** ProductServiceImpl.createProduct() completed for product ID: {}", productId);

        return productId;
    }

    @Override
    public String createProductSync(CreateProductDto createProductDto) throws Exception {
        String productId = UUID.randomUUID().toString();

        // TODO: Save product to the database before publishing event

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId,
                createProductDto.getTitle(),
                createProductDto.getPrice(),
                createProductDto.getQuantity()
        );

        logger.info("***** BEFORE ProductServiceImpl.createProduct() for product ID: {}", productId);

        SendResult<String, ProductCreatedEvent> sendResult
                = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();

        logger.info("Partition: {}", sendResult.getRecordMetadata().partition());
        logger.info("Topic: {}", sendResult.getRecordMetadata().topic());
        logger.info("Offset: {}", sendResult.getRecordMetadata().offset());

        logger.info("***** ProductServiceImpl.createProduct() completed for product ID: {}", productId);

        return productId;
    }

}
