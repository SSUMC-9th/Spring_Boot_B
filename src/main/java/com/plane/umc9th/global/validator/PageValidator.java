package com.plane.umc9th.global.validator;

import com.plane.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.plane.umc9th.global.apiPayload.exception.PageException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) {
            throw new PageException(GeneralErrorCode.PAGE_BAD_REQUEST);
        }
        return true;
    }
}