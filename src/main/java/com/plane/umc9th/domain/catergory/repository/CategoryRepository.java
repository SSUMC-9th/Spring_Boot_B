package com.plane.umc9th.domain.catergory.repository;

import com.plane.umc9th.domain.catergory.entity.FoodCatergory;
import com.plane.umc9th.domain.member.entity.Member;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<FoodCatergory, Long> {
}

