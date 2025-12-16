package com.plane.umc9th.domain.review.service;

import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.member.exception.repository.MemberRepository;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.plane.umc9th.domain.review.converter.ReviewConverter;
import com.plane.umc9th.domain.review.dto.ReviewReqDTO;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewResDTO.CreateDTO create(ReviewReqDTO.CreateDTO dto) {
        Member member = memberRepository.findById(dto.memberId()).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElse(null);

        Review review = ReviewConverter.toReview(dto);
        review.setMember(member);
        review.setRestaurant(restaurant);
        reviewRepository.save(review);

        return ReviewConverter.toCreateDTO(review);
    }

    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long memberId, String restaurantName, Integer ratingGroup, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> reviews = reviewRepository.findMyReviews(memberId, restaurantName, ratingGroup, pageRequest);
        return ReviewConverter.toReviewPreviewListDTO(reviews);
    }
}
