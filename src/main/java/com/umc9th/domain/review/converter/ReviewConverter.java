package com.umc9th.domain.review.converter;

import com.umc9th.domain.review.dto.res.ReviewResDTO;
import com.umc9th.domain.review.entity.Review;
import com.umc9th.domain.review.entity.ReviewPhoto;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.Reviewing toReviewDTO(Review reviewing){

        // 1. ReviewPhoto 목록에서 photoUrl 필드 추출 (getPhotoUrl() 사용)
        //Service에서 Fetch Join이 안되어 있으면 여기서 LazyInitializationException 발생
        List<String> imageUrls = reviewing.getReviewPhotoList().stream()
                .map(ReviewPhoto::getPhotoUrl) // ReviewPhoto 엔티티의 Getter 사용
                .collect(Collectors.toList());

        // 2. DTO 빌드
        return ReviewResDTO.Reviewing.builder()
                .reviewId(reviewing.getId())
                .star(reviewing.getStar())
                .content(reviewing.getContent())
                .createdAt(reviewing.getCreatedAt())
                .userId(reviewing.getUser().getId())
                .userName(reviewing.getUser().getName())

                .reviewImageUrls(imageUrls)
                .build();
    }

    public static List<ReviewResDTO.Reviewing> toReviewingList(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewConverter::toReviewDTO) // toReviewDTO 메서드를 사용하여 개별 변환
                .collect(Collectors.toList());
    }
}