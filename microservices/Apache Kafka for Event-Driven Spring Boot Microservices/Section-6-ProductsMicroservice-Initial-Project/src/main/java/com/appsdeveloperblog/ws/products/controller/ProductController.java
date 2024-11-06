package com.appsdeveloperblog.ws.products.controller;

import com.appsdeveloperblog.ws.products.dto.CreateProductDto;
import com.appsdeveloperblog.ws.products.errors.ErrorMessage;
import com.appsdeveloperblog.ws.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/async")
    public ResponseEntity<String> createProductAsync(
            @RequestBody CreateProductDto createProductDto
    ) {
        String productId = productService.createProductAsync(createProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @PostMapping("/sync")
    public ResponseEntity<?> createProductSync(
            @RequestBody CreateProductDto createProductDto
    ) {
        String productId = null;
        try {
            productId = productService.createProductSync(createProductDto);
        } catch (Exception e) {
            logger.error("***** Failed to publish ProductCreatedEvent for product ID: {}. Exception: {}", productId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), "Failed to create product", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
