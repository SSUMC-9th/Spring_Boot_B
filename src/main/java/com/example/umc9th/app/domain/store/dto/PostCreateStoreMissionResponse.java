package com.example.umc9th.app.domain.store.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class PostCreateStoreMissionResponse {
    @Builder
    public record CreateStoreMissionDTO(
            Long missionId,
            LocalDateTime createdAt
    ) {
    }
}
