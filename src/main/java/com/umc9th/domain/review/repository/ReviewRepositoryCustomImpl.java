package com.umc9th.domain.review.repository;

import com.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.umc9th.domain.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Review> findReviewsByStoreIdAndStarRange(
            Long storeId, Integer star) {
        return queryFactory
                .selectFrom(review)
                .where(
                        review.store.Id.eq(storeId),
                        starEq(star) // 동적 필터링 로직은 유지
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }
    //별점(star) 값에 따른 동적 WHERE 조건(BooleanExpression)을 생성
    private BooleanExpression starEq(Integer star) {
        if (star == null) {
            return null;
        }

        return switch (star) {
            case 5 -> review.star.eq(5);
            case 4 -> review.star.eq(4);
            case 3 -> review.star.eq(3);
            case 2 -> review.star.eq(2);
            case 1 -> review.star.eq(1);
            default -> null;
        };
    }
}