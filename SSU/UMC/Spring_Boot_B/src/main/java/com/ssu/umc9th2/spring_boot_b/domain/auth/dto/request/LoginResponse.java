package com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request;

public record LoginResponse(
        Long userId,
        String email,
        String nickname
) {}
