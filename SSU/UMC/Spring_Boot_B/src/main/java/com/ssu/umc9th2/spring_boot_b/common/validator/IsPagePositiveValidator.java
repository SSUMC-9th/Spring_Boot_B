package com.ssu.umc9th2.spring_boot_b.common.validator;

import com.ssu.umc9th2.spring_boot_b.common.annotation.IsPagePositive;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class IsPagePositiveValidator implements ConstraintValidator<IsPagePositive, Integer> {
    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {

        if(page == null || page < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Page number should be positive").addConstraintViolation();
            return false;
        }
        return true;
    }
}
