package com.example.umc9th.app.domain.review.controller;


import com.example.umc9th.app.domain.review.dto.PostCreateReviewRequest;
import com.example.umc9th.app.domain.review.dto.GetMyReviewResponse;
import com.example.umc9th.app.domain.review.dto.ReviewResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.app.domain.review.service.ReviewQueryService;
import com.example.umc9th.app.domain.review.service.ReviewService;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;

import com.example.umc9th.infra.validator.ValidPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {
    //사용할 서비스 객체 명시
    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    //Post 요청 담당
    @PostMapping("")
    public ApiResponse<Void> createReview(@RequestBody @Valid PostCreateReviewRequest request) {
        reviewService.createReview(request);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, null);
    }

    @GetMapping("/search")
    public ApiResponse<List<Review>> searchReview(@RequestParam String query, @RequestParam String type) {
        List<Review> dto = reviewQueryService.searchReview(type, query);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }

    @GetMapping("/my")
    public ApiResponse<Page<GetMyReviewResponse>> getMyReviews(@RequestParam Long memberId, @RequestParam(required = false) String storeName, @RequestParam(required = false) Float rating, @ValidPage @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());
        Page<GetMyReviewResponse> dto = reviewQueryService.getMyReviews(memberId, storeName, rating, pageRequest);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }

    @GetMapping
    public ApiResponse<ReviewResponse.ReviewPreViewList> getReviews(@RequestParam String storeName,@ValidPage @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName,pageRequest));
    }
}
