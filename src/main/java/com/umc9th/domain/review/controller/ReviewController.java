package com.umc9th.domain.review.controller;

import com.umc9th.domain.review.entity.Review;
import com.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping

    public ResponseEntity<List<Review>> getStoreReviews(
            @PathVariable("storeId") Long storeId,
            @RequestParam(name = "star", required = false) Integer star) {

        // Service의 'SearchByFilter' 메서드를 호출
        List<Review> reviews = reviewService.SearchByFilter(
                storeId,
                star
        );

        return ResponseEntity.ok(reviews);
    }
}