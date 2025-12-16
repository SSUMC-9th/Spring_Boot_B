package com.example.umc9th.app.domain.review.repository;

import com.example.umc9th.app.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
//queryDsl을 이용한 Review 커스텀 검색용 쿼리 정의 인터페이스 -> 구현체 (Impl)에서 실제 쿼리 작성
public interface ReviewQueryDsl {
    //조건(predicate)에 맞는 리뷰 목록 조회
    List<Review> searchReview(Predicate predicate);
    //조건(predicate)에 맞는 리뷰 목록을 페이징 처리하여 조회
    Page<Review> searchReview(Predicate predicate, Pageable pageable);
}
