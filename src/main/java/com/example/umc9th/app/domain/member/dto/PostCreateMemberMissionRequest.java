package com.example.umc9th.app.domain.member.dto;

import jakarta.validation.constraints.NotNull;

public class PostCreateMemberMissionRequest {

    public record DTO(
            @NotNull
            Long missionId,
            @NotNull
            Long memberId
    ) {}
}