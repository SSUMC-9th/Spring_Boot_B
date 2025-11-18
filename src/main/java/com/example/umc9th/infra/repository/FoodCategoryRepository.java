package com.example.umc9th.infra.repository;

import com.example.umc9th.infra.entity.FoodCategory;
import com.example.umc9th.infra.enums.FoodCategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

    Optional<FoodCategory> findByName(FoodCategoryName name);
}