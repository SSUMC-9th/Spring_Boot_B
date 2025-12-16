package com.plane.umc9th.domain.review.repository;

import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryForDSL {
    Page<Review> findAllByRestaurant(Restaurant store, Pageable pageRequest);
}
