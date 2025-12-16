package com.example.umc9th.app.domain.review.converter;

import com.example.umc9th.app.domain.review.dto.ReviewResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {

    // result -> DTO
    public static ReviewResponse.ReviewPreViewList toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResponse.ReviewPreViewList.builder()
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

    public static ReviewResponse.ReviewPreView toReviewPreviewDTO(
            Review review
    ){
        return ReviewResponse.ReviewPreView.builder()
                .memberName(review.getMember().getName())
                .rating(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}