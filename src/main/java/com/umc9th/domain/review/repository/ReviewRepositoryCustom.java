package com.umc9th.domain.review.repository;

import com.umc9th.domain.review.entity.Review;
import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findReviewsByStoreIdAndStarRange(
            Long storeId,
            Integer star
    );
}