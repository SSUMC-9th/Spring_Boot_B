package com.example.umc9th.app.domain.test.dto.response;

import lombok.Builder;
import lombok.Getter;

public class TestResponseDTO {

    @Builder
    @Getter
    //정상 응답을 반환할 때 사용
    public static class Testing {
        private String testString;
    }

    @Builder
    @Getter
    //예외 응답을 반환할 때 사용
    public static class Exception {
        private String testString;
    }
}
