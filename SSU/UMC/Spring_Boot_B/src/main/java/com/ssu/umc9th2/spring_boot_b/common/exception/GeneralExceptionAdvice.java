package com.ssu.umc9th2.spring_boot_b.common.exception;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseStatus;
import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionAdvice  {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException e) {
        if (e.getErrorStatus().getHttpStatus().is5xxServerError()) {
            log.error("[*] GeneralException :", e);
        } else {
            log.warn("[*] GeneralException : {}", e.getMessage());
        }
        return ApiResponse.error(e.getErrorStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ApiResponse.error(ErrorStatus.INVALID_PAGE_REQUEST,message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().iterator().next().getMessage();
        return ApiResponse.error(ErrorStatus.INVALID_PAGE_REQUEST, message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorMessage = "잘못된 요청입니다: " + e.getMessage();
        log.error("[*] IllegalArgumentException :", e);
        return ApiResponse.error(ErrorStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<Void>> handleNullPointerException(NullPointerException e) {
        String errorMessage = "서버에서 예기치 않은 오류가 발생했습니다. 요청을 처리하는 중에 Null 값이 참조되었습니다.";
        log.error("[*] NullPointerException :", e);
        return ApiResponse.error(ErrorStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("[*] Internal Server Error :", e);
        return ApiResponse.error(ErrorStatus.INTERNAL_SERVER_ERROR);
    }


    private ApiResponse<Void> createApiResponse(BaseStatus errorStatus, String errorMessage) {
        return new ApiResponse<>(
                false,
                errorStatus.getCode(),
                (errorMessage != null ? errorMessage : errorStatus.getMessage()),
                null
        );
    }
}
