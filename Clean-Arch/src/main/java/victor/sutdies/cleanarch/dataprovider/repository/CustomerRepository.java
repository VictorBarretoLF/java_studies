package victor.sutdies.cleanarch.dataprovider.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import victor.sutdies.cleanarch.dataprovider.repository.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}

