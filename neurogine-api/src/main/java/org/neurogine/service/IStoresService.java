package org.neurogine.service;

import org.neurogine.entity.Stores;

import java.util.List;

public interface IStoresService {
    Stores save(Stores stores);

    Stores update(Stores stores);

    Stores findById(String storeId);

    void delete(String storeId);

    List<Stores> findAll();
}
