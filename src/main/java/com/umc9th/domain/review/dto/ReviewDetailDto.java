package com.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewDetailDto {

    private Long reviewId;          // Review.id
    private String nickname;        // User.name
    private int star;               // Review.star
    private String reviewContent;   // Review.content
    private LocalDateTime createdAt;  // BaseEntity.createdAt
    private String feedbackContent; // ReviewFeedback.feedbackContent (피드백이 없는 경우 null)

}