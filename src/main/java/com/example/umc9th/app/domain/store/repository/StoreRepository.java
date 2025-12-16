package com.example.umc9th.app.domain.store.repository;

import com.example.umc9th.app.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findByName(String name);
}
