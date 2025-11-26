package com.plane.umc9th.domain.review.dto;

import com.plane.umc9th.domain.member.validator.ExistMember;
import com.plane.umc9th.domain.restaurant.validator.ExistRestaurant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewReqDTO {
    @Builder
    public record CreateDTO(
            @ExistRestaurant
            Long restaurantId,
            @ExistMember
            Long memberId,
            @NotNull
            int rating,
            @NotBlank
            String content
    ) {}
}
