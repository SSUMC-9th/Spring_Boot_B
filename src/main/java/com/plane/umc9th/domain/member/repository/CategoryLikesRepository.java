package com.plane.umc9th.domain.member.repository;

import com.plane.umc9th.domain.member.entity.FoodCatergoryLikes;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryLikesRepository extends JpaRepository<FoodCatergoryLikes, Long> {
}
