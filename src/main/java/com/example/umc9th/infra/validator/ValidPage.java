package com.example.umc9th.infra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target({ ElementType.PARAMETER })   // 메서드 파라미터에 붙이겠다
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

    String message() default "page는 1 이상이어야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}