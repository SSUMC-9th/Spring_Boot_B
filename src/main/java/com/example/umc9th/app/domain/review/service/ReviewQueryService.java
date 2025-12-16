package com.example.umc9th.app.domain.review.service;

import com.example.umc9th.app.domain.review.converter.ReviewConverter;
import com.example.umc9th.app.domain.review.dto.GetMyReviewResponse;

import static com.example.umc9th.app.domain.review.entity.QReview.review;

import com.example.umc9th.app.domain.review.dto.ReviewResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.review.repository.ReviewRepository;
import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.app.domain.store.exception.StoreErrorCode;
import com.example.umc9th.app.domain.store.exception.StoreException;
import com.example.umc9th.app.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public List<Review> searchReview(String type, String query) {
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
        return reviewRepository.searchReview(builder);

    }

    @Transactional(readOnly = true)
    public Page<GetMyReviewResponse> getMyReviews(Long memberId, String storeName, Float rating, PageRequest pageRequest) {
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


        Page<Review> page = reviewRepository.searchReview(builder, pageRequest);

        return page.map(r -> new GetMyReviewResponse(r.getId(), r.getStore().getName(), r.getRating(), r.getContent(), r.getCreatedAt()));
    }

    @Transactional(readOnly = true)
    public ReviewResponse.ReviewPreViewList findReview(String storeName, PageRequest pageRequest) {
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}

