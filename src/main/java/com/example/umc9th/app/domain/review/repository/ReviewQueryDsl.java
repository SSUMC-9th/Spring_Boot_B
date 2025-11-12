package com.example.umc9th.app.domain.review.repository;

import com.example.umc9th.app.domain.review.entity.Review;
import com.querydsl.core.Query;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(Predicate predicate);
    Page<Review> searchReview(Predicate predicate, Pageable pageable);
}
