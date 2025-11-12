package com.plane.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK,
            "OK200",
            "Request Success"),
    REVIEW_OK(HttpStatus.OK,
            "REVIEW_TEST200",
            "There are your reviews"),
    CREATED(HttpStatus.CREATED,
            "CREATED201",
            "Request Success")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
