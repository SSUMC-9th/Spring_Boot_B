package com.example.umc9th.app.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public class PostMemberLoginRequest {
    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ) {
    }
}
