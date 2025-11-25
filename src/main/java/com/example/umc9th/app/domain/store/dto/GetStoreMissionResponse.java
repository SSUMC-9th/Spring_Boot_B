package com.example.umc9th.app.domain.store.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GetStoreMissionResponse(
    Long missionId,
    Long storeId,
    Long reward,
    Long cashRequirement,
    LocalDate dueDate
){}
