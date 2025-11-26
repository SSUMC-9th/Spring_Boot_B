package com.ssu.umc9th2.spring_boot_b.domain.review.repository;


import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewCustomRepository {

    Page<GetUserReviewResponse> findUserReviewList(Long userId, Pageable pageable);
}
