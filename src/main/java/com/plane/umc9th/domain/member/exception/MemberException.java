package com.plane.umc9th.domain.member.exception;

import com.plane.umc9th.global.apiPayload.code.BaseErrorCode;
import com.plane.umc9th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}