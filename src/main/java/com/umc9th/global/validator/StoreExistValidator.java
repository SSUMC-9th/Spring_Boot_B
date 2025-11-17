package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ExistStore;
import com.example.umc9th.domain.store.repository.StoreRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        // 1) null이면 바로 오류
        if (value == null) {
            makeError(context, "storeId는 필수 값입니다.");
            return false;
        }

        // 2) 존재 여부 체크
        if (!storeRepository.existsById(value)) {
            makeError(context, "존재하지 않는 가게입니다.");
            return false;
        }

        return true;
    }

    private void makeError(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}