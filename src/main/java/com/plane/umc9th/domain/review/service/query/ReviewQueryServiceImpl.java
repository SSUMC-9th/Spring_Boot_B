package com.plane.umc9th.domain.review.service.query;

import com.plane.umc9th.domain.catergory.exception.CategoryException;
import com.plane.umc9th.domain.catergory.exception.code.CategoryErrorCode;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.plane.umc9th.domain.review.converter.ReviewConverter;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.exception.ReviewException;
import com.plane.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReviews(String storeName, Integer page) {
        Restaurant store = restaurantRepository.findByName(storeName)
                .orElseThrow(()-> new ReviewException(ReviewErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByRestaurant(store, pageRequest);
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
