package com.example.umc9th.app.domain.member.repository;

import com.example.umc9th.app.domain.member.entity.MemberFoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodCategoryRepository extends JpaRepository<MemberFoodCategory,Long> {
}
