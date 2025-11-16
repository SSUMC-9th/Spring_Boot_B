package com.ssu.umc9th2.spring_boot_b.domain.review.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.review.dto.request.CreateReviewRequest;
import com.ssu.umc9th2.spring_boot_b.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Tag(name = "리뷰")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 추가", description = "식당 리뷰 추가하기")
    @PostMapping("")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201",description = "리뷰 추가 성공",content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저 또는 식당이 존재하지 않을 때", content =@Content())
    public ResponseEntity<ApiResponse<Void>> createReview(@RequestBody @Valid CreateReviewRequest request){
        reviewService.createReview(request);
        return ApiResponse.success(SuccessStatus.CREATE_REVIEW_SUCCESS);
    }
}
