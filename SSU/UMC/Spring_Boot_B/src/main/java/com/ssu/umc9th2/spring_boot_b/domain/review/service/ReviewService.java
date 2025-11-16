package com.ssu.umc9th2.spring_boot_b.domain.review.service;

import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.service.RestaurantService;
import com.ssu.umc9th2.spring_boot_b.domain.review.dto.request.CreateReviewRequest;
import com.ssu.umc9th2.spring_boot_b.domain.review.entity.Review;
import com.ssu.umc9th2.spring_boot_b.domain.review.entity.ReviewImage;
import com.ssu.umc9th2.spring_boot_b.domain.review.repository.ReviewImageRepository;
import com.ssu.umc9th2.spring_boot_b.domain.review.repository.ReviewRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;

    public void createReview(CreateReviewRequest request) {
        User user = userService.getUserByUserId(request.userId());
        Restaurant restaurant = restaurantService.getRestaurantByRestaurantId(request.restaurantId());

        Review review = Review.builder()
                .user(user)
                .content(request.content())
                .rating(request.rating())
                .restaurant(restaurant)
                .build();

        Review savedReview = reviewRepository.save(review);

        if (request.reviewImageList() != null && request.reviewImageList().length > 0) {
            List<ReviewImage> reviewImages = Arrays.stream(request.reviewImageList())
                    .filter(img -> img != null && !img.isBlank())
                    .map(img -> ReviewImage.builder()
                            .review(savedReview)
                            .imageUrl(img)
                            .build())
                    .toList();

            reviewImageRepository.saveAll(reviewImages);
        }
    }
}
