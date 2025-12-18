package com.ssu.umc9th2.spring_boot_b.common.status;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorStatus implements BaseStatus {

    // 예시
    ERROR_STATUS("UMC_400", HttpStatus.BAD_REQUEST, "Bad Request"),
    BAD_REQUEST("UMC_400", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED("UMC_401", HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    FORBIDDEN("UMC_403", HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    NOT_FOUND("UMC_404", HttpStatus.NOT_FOUND, "요청한 자원을 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED("UMC_405", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드입니다."),
    INTERNAL_SERVER_ERROR("UMC_500", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),

    LOCATION_NOT_FOUND("LOCATION_404",HttpStatus.NOT_FOUND, "해당 지역이 존재하지 않습니다."),
    RESTAURANT_NOT_FOUND("RESTAURANT_404",HttpStatus.NOT_FOUND, "해당 식당이 존재하지 않습니다."),
    USER_NOT_FOUND("USER404",HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    MISSION_NOT_FOUND("MISSION404",HttpStatus.NOT_FOUND, "미션이 존재하지 않습니다."),

    RESTAURANT_ALREADY_EXIST("RESTAURANT_409", HttpStatus.CONFLICT, "이미 존재하는 식당입니다."),
    USER_MISSION_ALREADY_EXIST("USER_MISSION_409", HttpStatus.CONFLICT, "이미 존재하는 유저 미션입니다."),

    EMAIL_NOT_FOUND("AUTH_404", HttpStatus.NOT_FOUND, "존재하지 않는 유저 이메일입니다"),
    INVALID_PASSWORD("AUTH_401", HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다."),

    /**
     * JWT
     */
    JWT_TOKEN_NOT_FOUND("JWT_401", HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    JWT_INVALID_SIGNATURE("JWT_401", HttpStatus.UNAUTHORIZED, "잘못된 JWT 서명입니다."),
    JWT_MALFORMED("JWT_401", HttpStatus.UNAUTHORIZED, "잘못된 JWT 형식입니다."),
    JWT_EXPIRED("JWT_401", HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    JWT_UNSUPPORTED("JWT_401", HttpStatus.UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    JWT_INVALID("JWT_401", HttpStatus.UNAUTHORIZED, "JWT 토큰이 잘못되었습니다."),
    JWT_EXTRACT_ID_FAILED("JWT_401", HttpStatus.UNAUTHORIZED, "토큰에서 사용자 정보를 추출할 수 없습니다."),
    JWT_GENERAL_ERROR("JWT_401", HttpStatus.UNAUTHORIZED, "JWT 토큰 처리 중 알 수 없는 오류가 발생했습니다."),
    JWT_INVALID_TYPE("JWT_401", HttpStatus.UNAUTHORIZED, "토큰 타입이 유효하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND("JWT_401", HttpStatus.UNAUTHORIZED, "DB에 저장된 토큰과 일치하지 않습니다."),
    REFRESH_TOKEN_MISMATCH("JWT_401", HttpStatus.UNAUTHORIZED, "리프레시 토큰 정보가 사용자 정보와 일치하지 않습니다."),
    JWT_EXTRACT_ROLE_FAILED("JWT_401", HttpStatus.UNAUTHORIZED, "토큰에서 사용자 Role을 추출할 수 없습니다."),

    // 페이징
    INVALID_PAGE_REQUEST("COM_400", HttpStatus.BAD_REQUEST,"페이지 값은 null이거나 0이하일 수 없습니다.");
    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
