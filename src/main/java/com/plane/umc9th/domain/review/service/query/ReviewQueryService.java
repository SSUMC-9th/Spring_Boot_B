package com.plane.umc9th.domain.review.service.query;

import com.plane.umc9th.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreViewListDTO findReviews(String storeName, Integer page);
}
