package org.neurogine.service;

import lombok.RequiredArgsConstructor;
import org.neurogine.entity.Stores;
import org.neurogine.exception.ResourceNotFoundException;
import org.neurogine.repository.StoresRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.neurogine.utils.CommonHelper.getData;

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

    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<Stores> pages = storesRepository.findAll(pageable);
        getData(pageable, pages, response);
        return response;
    }

    @Override
    public Map<String, Object> findByCategoryOrName(String name, String category, Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<Stores> pages = storesRepository.findByCategoryOrName(name, category, pageable);
        getData(pageable, pages, response);
        return response;
    }
}
