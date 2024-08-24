package com.victorbarreto.hexagonal.adapters.out.repository;

import com.victorbarreto.hexagonal.adapters.out.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerEntityRepository extends MongoRepository<CustomerEntity, String> {
}
