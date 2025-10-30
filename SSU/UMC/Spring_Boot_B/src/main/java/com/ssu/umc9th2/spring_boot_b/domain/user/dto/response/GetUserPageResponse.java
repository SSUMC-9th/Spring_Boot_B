package com.ssu.umc9th2.spring_boot_b.domain.user.dto.response;

public record GetUserPageResponse(
        String nickname,
        String profile_link,
        String email,
        String phone,
        Long point
) {
}
