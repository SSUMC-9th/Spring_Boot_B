package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.review.dto.req.ReviewCreateRequest;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.annotation.ExistStore;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Created> createReview(@PathVariable("storeId") @ExistStore Long storeId, @Valid @RequestBody ReviewCreateRequest request)
    {
        ReviewResDTO.Created result = reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

}
