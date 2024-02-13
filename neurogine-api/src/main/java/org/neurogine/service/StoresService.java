package org.neurogine.service;

import lombok.RequiredArgsConstructor;
import org.neurogine.entity.Stores;
import org.neurogine.exception.ResourceNotFoundException;
import org.neurogine.repository.StoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoresService implements IStoresService {

    private final StoresRepository storesRepository;

    @Override
    public Stores save(Stores store) {
        return storesRepository.save(store);
    }

    @Override
    public Stores update(Stores store) {
        return storesRepository.save(store);
    }

    @Override
    public Stores findById(String storeId) {
        return storesRepository.findById(storeId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(String storeId) {
        Stores store = storesRepository.findById(storeId).orElseThrow(ResourceNotFoundException::new);
        storesRepository.delete(store);
    }

    public List<Stores> findAll() {
        return storesRepository.findAll();
    }
}
