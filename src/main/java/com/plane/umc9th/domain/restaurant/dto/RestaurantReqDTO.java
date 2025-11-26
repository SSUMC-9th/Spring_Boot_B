package com.plane.umc9th.domain.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class RestaurantReqDTO {
    @Builder
    public record CreateDTO(
            @NotNull
            long foodCatergoryId,
            @NotBlank
            String name,
            @NotBlank
            String description,
            @NotBlank
            String address,
            String openingHours
    ) {}
}
