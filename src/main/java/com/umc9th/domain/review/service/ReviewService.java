package com.umc9th.domain.review.service;

import com.umc9th.domain.review.entity.Review;
import com.umc9th.domain.review.exception.ReviewException;
import com.umc9th.domain.review.repository.ReviewRepository;
import com.umc9th.domain.review.dto.res.ReviewResDTO;
import com.umc9th.domain.review.converter.ReviewConverter;
import com.umc9th.domain.store.repository.StoreRepository;
import com.umc9th.global.apiPayload.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 메서드 반환 타입을 DTO 리스트로 변경
    public List<ReviewResDTO.Reviewing> searchByFilter(
            Long storeId, Integer star) {

        if ((storeId != null) && !storeRepository.existsById(storeId)) {
            throw new ReviewException(GeneralErrorCode.NOT_FOUND);
        }

        //필터링 결과가 0개인 것은 오류가 아니므로 그대로 빈 리스트 반환
        List<Review> reviewList = reviewRepository.findReviewsByStoreIdAndStarRange(storeId, star);

        //DTO로 변환
        return ReviewConverter.toReviewingList(reviewList);

    }
}