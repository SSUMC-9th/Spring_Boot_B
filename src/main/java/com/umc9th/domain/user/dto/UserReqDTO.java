package com.example.umc9th.domain.user.dto;

import com.example.umc9th.domain.user.enums.Address;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.global.annotation.ExistCategories;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birthday,
            @NotNull
            Address address,
            @ExistCategories
            List<Long> preferCategory
    ){}

    //로그인dto
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
