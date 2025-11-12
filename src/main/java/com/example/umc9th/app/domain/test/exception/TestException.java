package com.example.umc9th.app.domain.test.exception;

import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import com.example.umc9th.infra.apiPayload.exception.GeneralException;

//GeneralException에서 Test 관련 예외만 추가
public class TestException extends GeneralException {
    //BaseErrorCode를 받아옴(status, code, message)
    public TestException(BaseErrorCode code){
        //TestException을 받아서 부모인 GeneralException에 전달
        super(code);
    }
}
