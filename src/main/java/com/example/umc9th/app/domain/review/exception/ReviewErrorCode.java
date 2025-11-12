package com.example.umc9th.app.domain.review.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum ReviewErrorCode implements BaseErrorCode {

    // For test
    REVIEW_EXCEPTION(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰에러 발생"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}