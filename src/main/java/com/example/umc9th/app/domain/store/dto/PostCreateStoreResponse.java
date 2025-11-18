package com.example.umc9th.app.domain.store.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public record PostCreateStoreResponse() {
    @Builder
    public record CreateStoreDTO(
            Long storeId,
            LocalDateTime createdAt
    ) {
    }
}