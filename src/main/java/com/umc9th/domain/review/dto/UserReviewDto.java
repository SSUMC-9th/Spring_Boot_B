package com.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class UserReviewDto {
    private Long reviewId;
    private String storeName;   // 리뷰가 작성된 상점 이름
    private int star;
    private String content;
    private LocalDateTime createdAt;
}
