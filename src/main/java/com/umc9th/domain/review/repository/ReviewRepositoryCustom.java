package com.umc9th.domain.review.repository;

import com.umc9th.domain.review.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// QueryDSL 구현 메서드를 정의하는 인터페이스
public interface ReviewRepositoryCustom {
    List<Review> findReviewsByStoreIdAndStarRange(
            Long storeId,
            Integer starRating
    );
}