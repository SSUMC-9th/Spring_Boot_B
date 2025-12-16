package com.example.umc9th.domain.user.dto;

import lombok.Builder;

public class UserResDTO {

    // 로그인
    @Builder
    public record LoginDTO(
            Long userId,
            String accessToken
    ){}
}
