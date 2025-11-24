package com.example.umc9th.infra.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum FoodErrorCode implements BaseErrorCode {

    // For test
    NOT_FOUND(HttpStatus.OK,
            "FOOD400_1",
            "일치하는 카테고리가 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}