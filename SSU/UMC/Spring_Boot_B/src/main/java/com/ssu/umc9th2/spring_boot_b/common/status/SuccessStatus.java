package com.ssu.umc9th2.spring_boot_b.common.status;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessStatus implements BaseStatus {

    SUCCESS_200("UMC_200", HttpStatus.OK, "성공입니다."),
    SUCCESS_201("UMC_201", HttpStatus.CREATED, "성공입니다."),
    SUCCESS_204("UMC_204", HttpStatus.NO_CONTENT, "성공입니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
