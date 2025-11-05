package com.example.umc9th.app.domain.store.service;

import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.app.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    public Store findStoreById(Long storeId) {
        return storeRepository.findById(storeId).orElse(null);
    }
}
