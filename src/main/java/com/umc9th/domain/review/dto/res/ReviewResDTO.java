package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    private List<Reviewing> reviewingList;
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Reviewing{
        // --- 1. 리뷰 자체 정보 ---
        private Long reviewId;
        private Integer star;
        private String content;
        private LocalDateTime createdAt;

        // --- 2. 작성자 정보 (User 엔티티 기반) ---
        private Long userId;
        private String userName;

        // --- 3. 사진 정보 (ReviewPhoto 엔티티 기반) ---
        private List<String> reviewImageUrls;
    }
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Created {
        private Long reviewId;
        private String content;
        private Integer star;
        private LocalDateTime createdAt;
    }
}
