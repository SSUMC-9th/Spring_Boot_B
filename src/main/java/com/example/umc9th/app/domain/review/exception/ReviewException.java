package com.example.umc9th.app.domain.review.exception;

import com.example.umc9th.infra.apiPayload.code.BaseErrorCode;
import com.example.umc9th.infra.apiPayload.exception.GeneralException;

//GeneralException에서 Review 관련 예외만 추가
public class ReviewException extends GeneralException {
    //BaseErrorCode를 받아옴(status, code, message)
    public ReviewException(BaseErrorCode code){
        //ReviewException을 받아서 부모인 GeneralException에 전달
        super(code);
    }
}
