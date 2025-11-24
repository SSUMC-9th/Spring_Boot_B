package com.plane.umc9th.domain.mission.exception.code;

import com.plane.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MISSION200_1",
            "미션를 성공적으로 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
