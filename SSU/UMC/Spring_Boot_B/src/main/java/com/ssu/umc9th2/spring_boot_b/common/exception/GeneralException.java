package com.ssu.umc9th2.spring_boot_b.common.exception;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseStatus;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final BaseStatus errorStatus;

    public GeneralException(BaseStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
}
