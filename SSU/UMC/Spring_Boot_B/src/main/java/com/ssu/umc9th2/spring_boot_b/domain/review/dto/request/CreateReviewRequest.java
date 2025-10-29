package com.ssu.umc9th2.spring_boot_b.domain.review.dto.request;

public record CreateReviewRequest(
        Long userId,
        Double rating,
        String content,
        String[] reviewImageList,
        Long restaurantId
) {
}
