package com.ssu.umc9th2.spring_boot_b.common.base;

import org.springframework.http.HttpStatus;

public interface BaseStatus {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
