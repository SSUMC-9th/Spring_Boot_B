package com.example.umc9th.app.domain.store.dto;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PostCreateStoreMissionRequest {
    public record CreateStoreMissionDTO(
            @NotNull
            Long storeId,
            @NotNull
            Long reward,
            @NotNull
            Long cashRequirement,
            @NotNull
            LocalDate dueDate
    ) {
    }
}
