package com.plane.umc9th.domain.test.exception;

import com.plane.umc9th.global.apiPayload.code.BaseErrorCode;
import com.plane.umc9th.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
