package com.plane.umc9th.domain.member.dto;

import com.plane.umc9th.domain.catergory.valiator.ExistCategory;
import com.plane.umc9th.domain.member.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            @ExistCategory
            List<Long> preferCategory
    ){}
}