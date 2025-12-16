package com.plane.umc9th.domain.review.exception.code;

import com.plane.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "REVIEW200_2",
            "리뷰를 성공적으로 불러왔습니다."),
    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰를 성공적으로 생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}