package com.plane.umc9th.domain.review.service;

import com.plane.umc9th.domain.review.entity.QReview;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements Review{
    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    @Override
    public List<Review> saerchReview(
            Predicate predictate
    ) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;

        return queryFactory.selectFrom(review).where(predictate).fetvh();
    }
}
