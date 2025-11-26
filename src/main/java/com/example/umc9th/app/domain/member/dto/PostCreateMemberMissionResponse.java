package com.example.umc9th.app.domain.member.dto;

import lombok.Builder;


public class PostCreateMemberMissionResponse {
    @Builder
    public record DTO(
            Long memberMissionId
    ) {
    }
}