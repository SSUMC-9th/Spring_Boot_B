package com.example.umc9th.app.domain.store.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum StoreErrorCode implements BaseErrorCode {

    // For test
    STORE_EXCEPTION(HttpStatus.BAD_REQUEST, "STORE400_1", "스토어 에러 발생"),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE400_2", "스토어 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}