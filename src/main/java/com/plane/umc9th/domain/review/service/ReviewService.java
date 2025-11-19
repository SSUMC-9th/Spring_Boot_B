package com.plane.umc9th.domain.review.service;

import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.member.repository.MemberRepository;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.plane.umc9th.domain.review.dto.ReviewCreate;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    public Review create(ReviewCreate dto) {
        Member member = memberRepository.findById(dto.memberId()).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElse(null);

        Review review = Review.builder()
                .content(dto.content())
                .rating(dto.rating())
                .member(member)
                .restaurant(restaurant)
                .build();
        return reviewRepository.save(review);
    }

    public List<Review> getMyReviews(Long memberId, String restaurantName, Integer ratingGroup) {
        return reviewRepository.findMyReviews(memberId, restaurantName, ratingGroup);
    }
}
