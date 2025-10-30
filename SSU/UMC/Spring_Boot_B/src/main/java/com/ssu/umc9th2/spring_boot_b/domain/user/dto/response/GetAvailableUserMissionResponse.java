package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

import java.time.LocalDateTime;

public record GetAvailableUserMissionResponse(
        Long missionId,
        String content,
        Long point,
        String restaurantName,
        String restaurantCategory,
        LocalDateTime deadline
) {
}
