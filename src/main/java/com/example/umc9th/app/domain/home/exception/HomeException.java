package com.example.umc9th.app.domain.home.exception;

import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import com.example.umc9th.infra.apiPayload.exception.GeneralException;

//GeneralException에서 Home 관련 예외만 추가
public class HomeException extends GeneralException {
    //BaseErrorCode를 받아옴(status, code, message)
    public HomeException(BaseErrorCode code){
        //HomeException을 받아서 부모인 GeneralException에 전달
        super(code);
    }
}
