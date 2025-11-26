package com.ssu.umc9th2.spring_boot_b.common.annotation;

import com.ssu.umc9th2.spring_boot_b.common.validator.IsPagePositiveValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsPagePositiveValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPagePositive {
    String message() default "Page must be positive";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
