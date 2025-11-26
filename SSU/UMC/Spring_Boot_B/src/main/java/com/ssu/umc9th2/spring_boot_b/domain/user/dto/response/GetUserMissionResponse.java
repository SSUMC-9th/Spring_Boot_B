package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

public record GetUserMissionResponse(
        Long missionId,
        String content,
        Long point,
        String restaurantName,
        Boolean isCompleted
) {
}
