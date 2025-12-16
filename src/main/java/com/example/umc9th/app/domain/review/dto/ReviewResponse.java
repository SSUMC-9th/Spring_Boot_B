package com.example.umc9th.app.domain.review.dto;

import lombok.Builder;


import java.time.LocalDate;
import java.util.List;

public class ReviewResponse {

    @Builder
    public record ReviewPreViewList(
            List<ReviewPreView> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreView(
            String memberName,
            Double rating,
            String body,
            LocalDate createdAt
    ){}
}

