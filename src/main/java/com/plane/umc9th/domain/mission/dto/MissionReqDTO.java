package com.plane.umc9th.domain.mission.dto;

import com.plane.umc9th.domain.member.validator.ExistMember;
import com.plane.umc9th.domain.restaurant.validator.ExistRestaurant;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionReqDTO {
    @Builder
    public record CreateMissionDTO(
            @ExistMember
            Long memberId,
            @ExistRestaurant
            Long restaurantId,
            @NotNull
            String description,
            @NotNull
            LocalDateTime deadline,
            @NotNull
            int minAmount,
            @NotNull
            int point
        ){}
}
