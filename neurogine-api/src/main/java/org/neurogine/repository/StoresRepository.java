package org.neurogine.repository;

import org.neurogine.entity.Stores;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends MongoRepository<Stores, String> {
}
