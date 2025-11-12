package com.example.umc9th.app.domain.review.repository;

import com.example.umc9th.app.domain.review.entity.QReview;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<Review> searchReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QStore store = QStore.store;
        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store)
                .where(predicate)
                .fetch();
    }
    @Override
    public Page<Review> searchReview(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QStore store   = QStore.store;

        List<Review> content = queryFactory.selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        Long total = queryFactory.select(review.count())
                .from(review)
                .leftJoin(review.store, store)
                .where(predicate)
                .fetchOne();

        long totalCount = total == null ? 0L : total;
        return new PageImpl<>(content, pageable, totalCount);
    }
}
