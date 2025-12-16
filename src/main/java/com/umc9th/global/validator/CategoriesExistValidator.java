package com.example.umc9th.global.validator;

import com.example.umc9th.domain.category.repository.CategoryRepository;
import com.example.umc9th.global.annotation.ExistCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final CategoryRepository categoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        // 1. null 여부 확인
        if (values == null || values.isEmpty()) {
            return true;
        }

        // 2. DB에서 해당 ID들이 존재하는지 확인
        // 요청한 카테고리 ID 개수와 실제 DB에서 찾은 카테고리 개수가 일치하는지 검증
        boolean isValid = values.stream()
                .allMatch(value -> categoryRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("일부 카테고리가 존재하지 않습니다.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}