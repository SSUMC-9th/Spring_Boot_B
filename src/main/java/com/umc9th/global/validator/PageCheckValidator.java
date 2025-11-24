package com.example.umc9th.global.validator;


import com.example.umc9th.global.annotation.PageCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageCheckValidator implements ConstraintValidator<PageCheck, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){

        if(value == null || value < 1){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("페이지 번호는 1 이상이어야 합니다.").addConstraintViolation();
            return false;
        }
        return true;
    }
}
