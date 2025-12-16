package com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response;

public record KakaoLoginResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long expiresIn,
        UserInfo userInfo
) { }
