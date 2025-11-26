package com.plane.umc9th.domain.review.repository;

import com.plane.umc9th.domain.restaurant.entity.QRestaurant;
import com.plane.umc9th.domain.review.entity.QReview;
import com.plane.umc9th.domain.review.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryForDSL {

    private final JPAQueryFactory queryFactory;

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