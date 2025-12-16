package com.plane.umc9th.domain.review.repository;

import com.plane.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryForDSL
{
    Page<Review> findMyReviews(Long memberId, String restaurantName, Integer ratingGroup, Pageable pageable);
}
