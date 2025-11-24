package com.plane.umc9th.domain.mission.handler;

import com.plane.umc9th.domain.mission.exception.MissionException;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import com.plane.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MissionExceptionAdvice {

    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(MissionException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ) {

        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }
}