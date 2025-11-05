package com.example.umc9th.app.domain.review.service;

import com.example.umc9th.app.domain.review.dto.GetMyReviewResponse;
import com.example.umc9th.app.domain.review.entity.QReview;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    public List<Review> searchReview(String type, String query) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("address")) {
            builder.and(review.store.address.city.contains(query));
        }
        if (type.equals("rating")) {
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.store.address.city.contains(firstQuery));
            builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
        }
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    @Transactional(readOnly = true)
    public Page<GetMyReviewResponse> getMyReviews(
            Long memberId,
            String storeName,
            Float rating,
            Pageable pageable
    ) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 본인 리뷰만
        builder.and(review.member.id.eq(memberId));

        // 가게명 필터(선택). 완전일치가 맞다면 eq, 부분검색이면 contains
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.eq(storeName));
        }

        // 별점대 필터(선택)
        if (rating != null) {
            builder.and(review.rating.goe(rating));
        }

        Predicate predicate = builder;
        Page<Review> page = reviewRepository.searchReview(predicate, pageable);

        return page.map(r -> new GetMyReviewResponse(
                r.getId(),
                r.getStore().getName(),
                r.getRating(),
                r.getContent(),
                r.getCreatedAt()
        ));
    }

}
