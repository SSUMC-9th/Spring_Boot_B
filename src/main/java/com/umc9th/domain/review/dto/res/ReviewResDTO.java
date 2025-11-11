package com.umc9th.domain.review.dto.res;

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
        //1. 리뷰 자체 정보
        private Long reviewId;
        private Integer star;
        private String content;
        private LocalDateTime createdAt;

        //2. 작성자 정보
        private Long userId;
        private String userName;

        //3. 사진 정보
        private List<String> reviewImageUrls;
    }
}
