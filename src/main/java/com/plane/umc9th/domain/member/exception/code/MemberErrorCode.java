package com.plane.umc9th.domain.member.exception.code;

import com.plane.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    INVALID(HttpStatus.UNAUTHORIZED,
            "MEMBER403_1",
            "로그인 X."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
