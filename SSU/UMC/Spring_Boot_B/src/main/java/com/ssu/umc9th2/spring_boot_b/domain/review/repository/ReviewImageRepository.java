package com.ssu.umc9th2.spring_boot_b.domain.review.repository;

import com.ssu.umc9th2.spring_boot_b.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
