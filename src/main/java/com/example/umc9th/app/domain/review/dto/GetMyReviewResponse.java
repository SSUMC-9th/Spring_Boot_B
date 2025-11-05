package com.example.umc9th.app.domain.review.dto;

import java.time.LocalDateTime;

public record GetMyReviewResponse(
        Long id,
        String storeName,
        Double rating,
        String content,
        LocalDateTime createdAt
) {}