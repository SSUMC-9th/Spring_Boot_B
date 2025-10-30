package com.ssu.umc9th2.spring_boot_b.domain.review.controller;

import com.ssu.umc9th2.spring_boot_b.domain.review.dto.request.CreateReviewRequest;
import com.ssu.umc9th2.spring_boot_b.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("")
    public void createReview(@RequestBody @Valid CreateReviewRequest request){
        reviewService.createReview(request);
    }
}
