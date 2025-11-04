package com.ssu.umc9th2.spring_boot_b.domain.review.repository;


import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserReviewResponse;

import java.util.List;

public interface ReviewCustomRepository {

    List<GetUserReviewResponse> findAllByUserWithFilter(Long userId, String restaurantName, Double rating);
}
