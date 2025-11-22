package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record GetUserReviewResponse(
     Long reviewId,
     Long userId,
     String nickname,
     String restaurantName,
     LocalDateTime createdAt,
     LocalDateTime updatedAt,
     Double rating,
     String content,
     List<String> reviewImageList,
     String answer
)
{}

