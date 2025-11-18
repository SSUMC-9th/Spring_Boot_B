package com.example.umc9th.app.domain.review.service;

import com.example.umc9th.app.domain.member.entity.Member;
import com.example.umc9th.app.domain.member.service.MemberService;
import com.example.umc9th.app.domain.review.dto.PostCreateReviewRequest;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.review.repository.ReviewRepository;
import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.app.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final StoreService storeService;
    public void createReview(PostCreateReviewRequest request){
        Member member = memberService.findMemberById(request.memberId());
        Store store = storeService.findStoreById(request.storeId());

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(request.content())
                .rating(request.rating())
                .build();

        Review savedReview = reviewRepository.save(review);
    }
}
