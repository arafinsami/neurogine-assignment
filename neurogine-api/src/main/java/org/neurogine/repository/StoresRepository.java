package org.neurogine.repository;

import org.neurogine.entity.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends MongoRepository<Stores, String> {

    Page<Stores> findAll(Pageable pageable);

    Page<Stores> findByCategoryOrName(String category, String name, Pageable pageable);
}
