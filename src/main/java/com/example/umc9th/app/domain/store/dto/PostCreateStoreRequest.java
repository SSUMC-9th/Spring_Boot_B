package com.example.umc9th.app.domain.store.dto;

import com.example.umc9th.app.domain.store.entity.StoreHours;
import com.example.umc9th.infra.dto.AddressRequest;
import com.example.umc9th.infra.entity.Address;
import com.example.umc9th.infra.entity.FoodCategory;

import com.example.umc9th.infra.enums.FoodCategoryName;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public class PostCreateStoreRequest {

    public record StoreHoursDTO(
            @NotNull Integer week,      // 1~7 (월~일) 같은 규칙 정해두면 좋음
            @NotNull LocalTime open,
            @NotNull LocalTime close,
            boolean overnight,          // 자정을 넘기는지
            boolean closed              // 휴무일인지
    ) { }
    public record CreateStoreDTO(
            @NotNull
            String name,
            String backgroundImg,
            @NotNull
            FoodCategoryName foodCategoryName,
            @NotNull
            List<StoreHoursDTO> storeHoursList,
            @NotNull
            AddressRequest addressRequest
    ) {
    }
}
