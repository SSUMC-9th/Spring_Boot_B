package com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response;

import java.time.LocalDateTime;

public record GetRestaurantMissionResponse(
        Long missionId,
        String content,
        Long point,
        LocalDateTime deadline,
        String restaurantName,
        String restaurantCategory,
        String restaurantAddress
) {
}
