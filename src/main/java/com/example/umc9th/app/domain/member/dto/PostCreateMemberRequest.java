package com.example.umc9th.app.domain.member.dto;

import com.example.umc9th.app.domain.member.entity.MemberFoodCategory;
import com.example.umc9th.app.domain.member.enums.Gender;
import com.example.umc9th.infra.annotation.ExistFoods;
import com.example.umc9th.infra.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class PostCreateMemberRequest {
    public record JoinDTO(
            @NotBlank
            String name,
            String email,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Address address,
            @ExistFoods
            List<Long> foodCategories

    ) {
    }
}
