package com.plane.umc9th.domain.review.repository;

import com.plane.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryForDSL
{
    List<Review> findMyReviews(Long memberId, String restaurantName, Integer ratingGroup);
}
