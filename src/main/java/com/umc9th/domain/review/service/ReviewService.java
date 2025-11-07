package com.umc9th.domain.review.service;

import com.umc9th.domain.review.entity.Review;
import com.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> SearchByFilter(Long storeId, Integer star) {
        return reviewRepository.findReviewsByStoreIdAndStarRange(storeId, star);
    }
}