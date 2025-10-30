package com.plane.umc9th.domain.review.service;

import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.member.repository.MemberRepository;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.plane.umc9th.domain.review.dto.ReviewCreateDTO;
import com.plane.umc9th.domain.review.entity.Review;
import com.plane.umc9th.domain.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepositroy;
    private final MemberRepository memberRepositroy;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepositroy,  MemberRepository memberRepositroy,  RestaurantRepository restaurantRepository) {
        this.reviewRepositroy = reviewRepositroy;
        this.memberRepositroy = memberRepositroy ;
        this.restaurantRepository = restaurantRepository;
    }

    public Review create(ReviewCreateDTO dto) {
        Member member = memberRepositroy.findById(dto.getMemberId()).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElse(null);

        Review review = new Review();
        review.setContent(dto.getContent());
        review.setRating(dto.getRating());
        review.setMember(member);
        review.setRestaurant(restaurant);
        return this.reviewRepositroy.save(review);
    }
}
