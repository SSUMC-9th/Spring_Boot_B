package com.example.umc9th.app.domain.member.exception.code;


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
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    INVALID(HttpStatus.BAD_REQUEST, "MEMBER_403_1" ,"유효하지 않은 접근입니다." );

    private final HttpStatus status;
    private final String code;
    private final String message;
}