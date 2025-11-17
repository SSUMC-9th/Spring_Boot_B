package com.example.umc9th.infra.apiPayload.code;

import org.springframework.http.HttpStatus;

//Error Code의 기본적인 형식
public interface BaseErrorCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();
}