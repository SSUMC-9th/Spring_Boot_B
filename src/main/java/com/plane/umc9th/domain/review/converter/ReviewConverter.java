package com.plane.umc9th.domain.review.converter;

import com.plane.umc9th.domain.restaurant.dto.RestaurantReqDTO;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.review.dto.ReviewReqDTO;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

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

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(
                        review.getMember() != null
                                ? review.getMember().getName()
                                : "알 수 없음")
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}