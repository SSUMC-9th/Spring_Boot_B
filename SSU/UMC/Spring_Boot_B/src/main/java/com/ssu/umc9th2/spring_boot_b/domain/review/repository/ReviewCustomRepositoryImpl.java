package com.ssu.umc9th2.spring_boot_b.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.ssu.umc9th2.spring_boot_b.domain.review.entity.QReview.review;
import static com.ssu.umc9th2.spring_boot_b.domain.review.entity.QReviewImage.reviewImage;
import static com.ssu.umc9th2.spring_boot_b.domain.review.entity.QReviewReply.reviewReply;
import static com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.QRestaurant.restaurant;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<GetUserReviewResponse> findUserReviewList(Long userId, Pageable pageable) {

        List<GetUserReviewResponse> content = jpaQueryFactory
                .from(review)
                .leftJoin(review.restaurant, restaurant)
                .leftJoin(reviewImage).on(reviewImage.review.eq(review))
                .leftJoin(reviewReply).on(reviewReply.review.eq(review))
                .where(review.user.id.eq(userId))
                .orderBy(review.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(
                        groupBy(review.id).list(
                                Projections.constructor(GetUserReviewResponse.class,
                                        review.id,
                                        review.user.id,
                                        review.user.nickname,
                                        restaurant.name,
                                        review.createdAt,
                                        review.updatedAt,
                                        review.rating,
                                        review.content,
                                        list(reviewImage.imageUrl),
                                        reviewReply.content
                                )
                        )
                );

        // count 쿼리 (단순)
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(review.count())
                .from(review)
                .where(review.user.id.eq(userId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }


}
