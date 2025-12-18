package com.example.umc9th.app.domain.member.dto;

import lombok.Builder;

public class PostMemberLoginResponse {
    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ) {
    }
}
