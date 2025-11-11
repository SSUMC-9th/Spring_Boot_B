package com.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 200 OK: 요청을 성공적으로 처리했음
    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청이 성공적으로 처리되었습니다."),

    // 201 Created: 요청이 성공했으며, 새로운 리소스가 생성되었음
    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스가 성공적으로 생성되었습니다."),

    // 202 Accepted: 요청은 접수되었으나, 처리가 완료되지 않았음
    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 접수되었습니다. (비동기 처리 등)"),

    // 204 No Content: 요청은 성공했으나, 응답 본문에 보낼 데이터가 없음 (ex. 삭제, 수정 성공)
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204_1",
            "요청이 성공적으로 처리되었으나, 반환할 내용이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}