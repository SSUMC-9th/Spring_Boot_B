package com.example.umc9th.app.domain.home.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum HomeErrorCode implements BaseErrorCode {

    // For home
    HOME_EXCEPTION(HttpStatus.BAD_REQUEST, "HOME400_1", "홈 에러 발생"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}