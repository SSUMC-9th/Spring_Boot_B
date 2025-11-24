package com.plane.umc9th.domain.review.controller;

import com.plane.umc9th.domain.review.dto.ReviewReqDTO;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.plane.umc9th.domain.review.service.ReviewService;
import com.plane.umc9th.domain.review.service.query.ReviewQueryService;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import com.plane.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @Operation(
            summary = "자신이 작성한 리뷰 목록 조회 API",
            description = "자신이 쓴 모든 리뷰를 조회합니다."
    )
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) Integer ratingGroup,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        GeneralSuccessCode code = GeneralSuccessCode.REVIEW_OK;
        return ApiResponse.onSuccess(
                code,
                reviewService.getMyReviews(memberId, restaurantName, ratingGroup, page-1));
    }

    @Operation(
            summary = "리뷰 작성 API",
            description = "리뷰를 작성합니다."
    )
    @PostMapping()
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateDTO dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewService.create(dto));
    }


    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, this.reviewQueryService.findReviews(storeName, page));
    }
}