package com.plane.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateDTO(
            Long reviewId,
            LocalDateTime createdAt
    ){}
}
