package com.example.umc9th.app.domain.mission.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//BaseErrorCode 구현(각 도메인별로 다르게)
public enum MissionErrorCode implements BaseErrorCode {

    // For test
    MISSION_EXCEPTION(HttpStatus.BAD_REQUEST, "MISSION400_1", "미션 에러 발생"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}