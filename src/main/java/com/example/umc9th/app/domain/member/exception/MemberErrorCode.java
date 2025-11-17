package com.example.umc9th.app.domain.member.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum MemberErrorCode implements BaseErrorCode {

    // For test
    MEMBER_EXCEPTION(HttpStatus.BAD_REQUEST, "MEMBER400_1", "멤버 에러 발생"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}