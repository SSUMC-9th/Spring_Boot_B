package com.plane.umc9th.domain.review.controller;

import com.plane.umc9th.domain.review.dto.ReviewReqDTO;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.service.ReviewService;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import com.plane.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/my")
    public ApiResponse<List<Review>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) Integer ratingGroup
    ) {
        GeneralSuccessCode code = GeneralSuccessCode.REVIEW_OK;
        return ApiResponse.onSuccess(
                code,
                reviewService.getMyReviews(memberId, restaurantName, ratingGroup));
    }

    @PostMapping()
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateDTO dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewService.create(dto));
    }
}