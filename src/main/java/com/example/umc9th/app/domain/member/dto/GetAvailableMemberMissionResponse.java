package com.example.umc9th.app.domain.member.dto;

import com.example.umc9th.infra.enums.FoodCategoryName;

import java.time.LocalDate;

public record GetAvailableMemberMissionResponse(
        Long missionId,
        FoodCategoryName foodCategoryName,
        Long reward,
        Long cashRequirement,
        String storeName,
        LocalDate dueDate
) {
}
