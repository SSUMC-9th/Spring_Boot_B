package com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

public record CreateRestaurantRequest(
        String name,
        String category,
        @Schema(type = "string", example = "09:00:00")
        LocalTime openTime,
        @Schema(type = "string", example = "09:00:00")
        LocalTime closeTime
) {
}
