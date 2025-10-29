package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

public record GetUserSummaryResponse(
        String nickname,
        String email,
        Long point,
        Integer notificationCount
) {
}
