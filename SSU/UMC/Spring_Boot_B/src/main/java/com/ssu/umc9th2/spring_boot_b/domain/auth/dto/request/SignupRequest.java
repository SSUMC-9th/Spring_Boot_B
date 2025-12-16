package com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request;

public record SignupRequest(
        String email,
        String password,
        String gender,
        String nickname
) {}
