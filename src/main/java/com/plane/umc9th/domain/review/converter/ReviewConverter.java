package com.plane.umc9th.domain.review.converter;

import com.plane.umc9th.domain.restaurant.dto.RestaurantReqDTO;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.review.dto.ReviewReqDTO;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;

public class ReviewConverter {

    // Entity -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(
            Review review
    ){
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Review toReview(
            ReviewReqDTO.CreateDTO dto
    ){
        return Review.builder()
                .content(dto.content())
                .rating(dto.rating())
                .build();
    }
}