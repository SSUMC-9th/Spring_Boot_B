package com.plane.umc9th.domain.review.service;

import com.plane.umc9th.domain.review.entity.QReview;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import com.plane.umc9th.domain.review.repository.ReviewRepositoryForDSL;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewRepositoryForDSL {
    private final ReviewRepository reviewRepository;
    private final EntityManager em;
    JPAQueryFactory queryFactory;

    @Override
    public List<Review> findMyReviews(Long memberId, String restaurantName, Integer ratingGroup) {
        QReview review = QReview.review;
        return queryFactory
                .selectFrom(review)
                .where(review.member.id.eq(memberId)
                        .and(review.restaurant.name.eq(restaurantName))
                        .and(review.rating.goe(ratingGroup))
                )
                .fetch();
    }
}
