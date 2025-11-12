package com.example.umc9th.app.domain.review.controller;


import com.example.umc9th.app.domain.review.dto.CreateReviewRequest;
import com.example.umc9th.app.domain.review.dto.GetMyReviewResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.review.service.ReviewQueryService;
import com.example.umc9th.app.domain.review.service.ReviewService;
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
    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("")
    public void createReview(@RequestBody @Valid CreateReviewRequest request) {
        reviewService.createReview(request);
    }

    @GetMapping("/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<Review> result = reviewQueryService.searchReview(type,query);
        return result;
}
    @GetMapping("/my")
    public Page<GetMyReviewResponse> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float rating,
            @PageableDefault(size = 10, sort = "updatedAt", direction = org.springframework.data.domain.Sort.Direction.DESC)
            Pageable pageable
    ) {
        return reviewQueryService.getMyReviews(memberId, storeName, rating, pageable);
    }

}
