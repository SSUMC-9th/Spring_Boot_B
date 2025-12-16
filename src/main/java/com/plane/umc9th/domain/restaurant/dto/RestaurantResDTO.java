package com.plane.umc9th.domain.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class RestaurantResDTO {
    @Builder
    public record CreateDTO(
            Long restaurantId,
            LocalDateTime createAt
    ){}
}