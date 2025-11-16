package com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.request;

import com.ssu.umc9th2.spring_boot_b.domain.location.entity.Location;

import java.time.LocalTime;

public record CreateRestaurantRequestWithLocation(
        String name,
        String category,
        LocalTime openTime,
        LocalTime closeTime,
        Location location
) {
}
