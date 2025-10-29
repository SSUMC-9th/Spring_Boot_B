package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

public record GetUserMissionStatusResponse(
        Long missionId,
        String content,
        Long point,
        String restaurantName,
        boolean isCompleted
) {
}
