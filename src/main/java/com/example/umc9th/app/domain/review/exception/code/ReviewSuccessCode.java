package com.example.umc9th.app.domain.review.exception.code;

import com.example.umc9th.infra.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "REVIEW200",
            "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "REVIEW201",
            "새로운 리소스가 생성되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "REVIEW204",
            "요청 성공, 반환할 데이터가 없습니다."),
    FOUND(HttpStatus.FOUND,
            "REVIEW200_1",
            "리뷰 조회 완료");
    private final HttpStatus status;
    private final String code;
    private final String message;

}




