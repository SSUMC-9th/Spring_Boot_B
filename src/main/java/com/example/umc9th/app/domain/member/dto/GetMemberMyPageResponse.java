package com.example.umc9th.app.domain.member.dto;

import jakarta.validation.constraints.NotNull;

public record GetMemberMyPageResponse(
        @NotNull String name,
        @NotNull String email,
        @NotNull Boolean isPhoneNumVerified,
        String phoneNum,
        Long point
        ) {
}

