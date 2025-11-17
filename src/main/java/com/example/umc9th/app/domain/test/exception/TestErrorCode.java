package com.example.umc9th.app.domain.test.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum TestErrorCode implements BaseErrorCode {

    // For test
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "이거는 테스트"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}