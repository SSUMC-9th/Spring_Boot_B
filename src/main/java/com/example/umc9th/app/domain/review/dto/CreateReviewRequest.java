package com.example.umc9th.app.domain.review.dto;

import jakarta.validation.constraints.NotNull;

public record CreateReviewRequest(
        @NotNull Long memberId,
        @NotNull Double rating,
        String content,
//        String[] ImageList,
        @NotNull Long storeId
) {
}
