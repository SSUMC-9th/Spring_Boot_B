package com.example.umc9th.app.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class PostCreateMemberResponse{
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}
}
