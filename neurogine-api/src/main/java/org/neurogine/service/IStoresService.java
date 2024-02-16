package org.neurogine.service;

import org.neurogine.entity.Stores;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IStoresService {
    Stores save(Stores stores);

    Stores update(Stores stores);

    void setUp();

    Stores findById(String storeId);

    void delete(String storeId);

    Map<String, Object> findAll(Pageable pageable);

    Map<String, Object> findByCategoryOrName(String category, String name, Pageable pageable);
}
