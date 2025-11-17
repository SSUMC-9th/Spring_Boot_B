package com.example.umc9th.app.domain.test.converter;

import com.example.umc9th.app.domain.test.dto.response.TestResponseDTO;

//보낼 Response를 DTO형식으로 변환해준다.
public class TestConverter {

    // 객체 -> DTO
    public static TestResponseDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResponseDTO.Testing.builder()
                .testString(testing)
                .build();
    }
    // 객체 -> DTO
    public static TestResponseDTO.Exception toExceptionDTO(
            String testing
    ){
        return TestResponseDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}