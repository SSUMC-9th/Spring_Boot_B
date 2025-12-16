package com.plane.umc9th.domain.review.service;

import com.plane.umc9th.domain.restaurant.entity.QRestaurant;
import com.plane.umc9th.domain.review.entity.QReview;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import com.plane.umc9th.domain.review.repository.ReviewRepositoryForDSL;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewRepositoryForDSL {
    private final ReviewRepository reviewRepository;
    private final EntityManager em;
    JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviews(Long memberId, String restaurantName, Integer ratingGroup, Pageable pageable) {
        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;

        var query = queryFactory
                .selectFrom(review)
                .leftJoin(review.restaurant, restaurant).fetchJoin()
                .where(review.member.id.eq(memberId));

        if (StringUtils.hasText(restaurantName)) {
            query.where(restaurant.name.containsIgnoreCase(restaurantName));
        }

        if (ratingGroup != null) {
            float min = ratingGroup - 0.5f;
            float max = ratingGroup;
            query.where(review.rating.between(min, max));
        }

        List<Review> results = query
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(review.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }
}