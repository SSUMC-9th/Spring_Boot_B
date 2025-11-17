package com.example.umc9th.app.domain.review.controller;


import com.example.umc9th.app.domain.review.dto.CreateReviewRequest;
import com.example.umc9th.app.domain.review.dto.GetMyReviewResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.review.service.ReviewQueryService;
import com.example.umc9th.app.domain.review.service.ReviewService;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    //사용할 서비스 객체 명시
    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    //Post 요청 담당
    @PostMapping("")
    public void createReview(@RequestBody @Valid CreateReviewRequest request) {
        reviewService.createReview(request);
    }

    @GetMapping("/search")
    public ApiResponse<List<Review>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        List<Review> dto = reviewQueryService.searchReview(type, query);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }

    @GetMapping("/my")
    public ApiResponse<Page<GetMyReviewResponse>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float rating,
            @PageableDefault(size = 10, sort = "updatedAt", direction = org.springframework.data.domain.Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<GetMyReviewResponse> dto = reviewQueryService.getMyReviews(memberId, storeName, rating, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }

}
