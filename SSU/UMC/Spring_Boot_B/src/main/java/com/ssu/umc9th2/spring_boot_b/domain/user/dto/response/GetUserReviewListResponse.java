package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

import java.util.List;

public record GetUserReviewListResponse(
        List<GetUserReviewResponse> userReviewList
) {
}
