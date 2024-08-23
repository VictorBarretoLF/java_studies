package com.victorbarreto.hexagonal.adapters.out.repository;

import com.victorbarreto.hexagonal.adapters.out.repository.entity.CustumerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustumerEntityRepository extends MongoRepository<CustumerEntity, String> {
}
