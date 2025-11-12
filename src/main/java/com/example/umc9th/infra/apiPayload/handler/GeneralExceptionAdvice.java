package com.example.umc9th.infra.apiPayload.handler;

import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import com.example.umc9th.infra.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.infra.apiPayload.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//전역 예외 처리 담당 어노테이션, 모든 컨트롤러의 예외를 여기서 처리한다.
@RestControllerAdvice
public class GeneralExceptionAdvice {

    //지정한 예외 타입을 처리하도록 스프링에 알려주는 어노테이션
    @ExceptionHandler(GeneralException.class)
    //GeneralException에 저장된 예외를 받아서 Response로 반환
    public ResponseEntity<ApiResponse<Void>> handleException(GeneralException ex) {

        //ApiResponse는 HTTP body에 들어갈 JSON 구조를 정의(isSuccess, code, message 등)
        //ResponseEntity는 HTTP 응답 전체를 감싸는 구조(status, header, body)
        //에러가 발생할 경우 status가 전부 달라서 어떤 예외인지 알려주기 위해 ResponseEntity에 ApiResponse를 넣어서 반환
        return ResponseEntity.status(ex.getCode().getStatus()).body(ApiResponse.onFailure(ex.getCode(), null));
    }
    //나머지 정의되지 않은 모든 예외를 처리한다.(500에러)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus()).body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}
