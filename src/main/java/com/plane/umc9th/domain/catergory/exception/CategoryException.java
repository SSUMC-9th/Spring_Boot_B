package com.plane.umc9th.domain.catergory.exception;

import com.plane.umc9th.global.apiPayload.code.BaseErrorCode;
import com.plane.umc9th.global.apiPayload.exception.GeneralException;

public class CategoryException extends GeneralException {
    public CategoryException(BaseErrorCode code) {
        super(code);
    }
}