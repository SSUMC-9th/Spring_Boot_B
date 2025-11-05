package com.plane.umc9th.domain.review.dto;

public record ReviewCreate(
        float rating,
        String content,
        Long memberId,
        Integer restaurantId
) {
}
