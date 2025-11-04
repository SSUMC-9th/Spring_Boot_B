package com.ssu.umc9th2.spring_boot_b.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.core.types.Projections.bean;
import static com.ssu.umc9th2.spring_boot_b.domain.review.entity.QReview.review;
import static com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.QRestaurant.restaurant;
import static com.ssu.umc9th2.spring_boot_b.domain.review.entity.QReviewImage.reviewImage;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GetUserReviewResponse> findAllByUserWithFilter(Long userId, String restaurantName, Double rating) {

        Map<Long, GetUserReviewResponse> result = jpaQueryFactory
                .from(review)
                .leftJoin(review.restaurant, restaurant)
                .leftJoin(reviewImage).on(reviewImage.review.eq(review))
                .where(
                        review.user.id.eq(userId)
                                .and(
                                        restaurantNameEq(restaurantName)
                                                .or(ratingEq(rating))
                                )
                )
                .transform(
                        groupBy(review.id).as(
                                bean(GetUserReviewResponse.class,
                                        review.id.as("reviewId"),
                                        review.user.id.as("userId"),
                                        review.user.nickname.as("nickname"),
                                        restaurant.name.as("restaurantName"),
                                        review.createdAt.as("createdAt"),
                                        review.updatedAt.as("updatedAt"),
                                        review.rating.as("rating"),
                                        review.content.as("content"),
                                        list(reviewImage.imageUrl).as("reviewImageList")
                                )
                        )
                );

        return result.values().stream().toList();
    }

    private BooleanExpression restaurantNameEq(String restaurantName) {
        return restaurantName != null ? review.restaurant.name.eq(restaurantName) : null;
    }

    private BooleanExpression ratingEq(Double rating) {
        return rating != null ? review.rating.eq(rating) : null;
    }
}
