package com.example.umc9th.infra.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode{

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
