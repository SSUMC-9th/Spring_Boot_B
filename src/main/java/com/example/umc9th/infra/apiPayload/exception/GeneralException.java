package com.example.umc9th.infra.apiPayload.exception;


import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//도메인별 Exception을 받아서 저장한다.
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
}
