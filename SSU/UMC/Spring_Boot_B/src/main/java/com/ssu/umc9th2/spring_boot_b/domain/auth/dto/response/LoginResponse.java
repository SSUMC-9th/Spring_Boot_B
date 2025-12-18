package com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response;

import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;

public record LoginResponse(
        Long userId,
        String email,
        String nickname,
        String accessToken,
        String refreshToken
) {
    public static LoginResponse from(
            String accessToken,
            String refreshToken,
            User user
    ) {
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                accessToken,
                refreshToken
        );
    }
}
