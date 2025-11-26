package com.example.umc9th.app.domain.review.controller;

import com.example.umc9th.app.domain.review.dto.GetMyReviewResponse;
import com.example.umc9th.app.domain.review.dto.PostCreateReviewRequest;
import com.example.umc9th.app.domain.review.dto.ReviewResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewControllerDocs {
    @Operation(summary = "리뷰 생성", description = "리뷰를 생성합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 생성 성공", content = @Content(schema = @Schema(implementation = Review.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<Void> createReview(@RequestBody @Valid PostCreateReviewRequest request);

    @Operation(summary = "가게의 리뷰 목록 조회(페이징)", description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")})
    ApiResponse<ReviewResponse.ReviewPreViewList> getReviews(@RequestParam String storeName, @ValidPage @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "리뷰 검색", description = "검색어 종류에 따라 리뷰를 검색합니다.\ntype=도시, 별점")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 검색 성공", content = @Content(schema = @Schema(implementation = Review.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<List<Review>> searchReview(@RequestParam String query, @RequestParam String type);

    @Operation(summary = "나의 리뷰 조회", description = "리뷰를 조회합니다. 가게명이나 별점에 맞춰 조회도 가능합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "나의 리뷰 조회 성공", content = @Content(schema = @Schema(implementation = Review.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<Page<GetMyReviewResponse>> getMyReviews(@RequestParam Long memberId, @RequestParam(required = false) String storeName, @RequestParam(required = false) Float rating, @ValidPage @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size);
}
