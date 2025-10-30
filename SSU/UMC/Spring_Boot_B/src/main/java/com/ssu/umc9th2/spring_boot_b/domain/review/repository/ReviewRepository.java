package com.ssu.umc9th2.spring_boot_b.domain.review.repository;

import com.ssu.umc9th2.spring_boot_b.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
