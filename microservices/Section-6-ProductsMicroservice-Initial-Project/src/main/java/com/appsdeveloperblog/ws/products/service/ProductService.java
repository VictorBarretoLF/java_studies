package com.appsdeveloperblog.ws.products.service;

import com.appsdeveloperblog.ws.products.dto.CreateProductDto;

public interface ProductService {

    String createProductAsync(CreateProductDto createProductDto);

    String createProductSync(CreateProductDto createProductDto) throws Exception;
}
