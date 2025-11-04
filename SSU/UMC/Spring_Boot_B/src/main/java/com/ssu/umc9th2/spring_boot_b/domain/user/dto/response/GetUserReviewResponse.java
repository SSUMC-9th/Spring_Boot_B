package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserReviewResponse {
    private Long reviewId;
    private Long userId;
    private String nickname;
    private String restaurantName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double rating;
    private String content;
    private List<String> reviewImageList;
}

