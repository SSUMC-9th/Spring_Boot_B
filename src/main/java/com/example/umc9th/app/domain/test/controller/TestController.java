package com.example.umc9th.app.domain.test.controller;

import com.example.umc9th.app.domain.test.converter.TestConverter;
import com.example.umc9th.app.domain.test.dto.response.TestResponseDTO;
import com.example.umc9th.app.domain.test.service.query.TestQueryService;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;


    @GetMapping("/test")

    public ApiResponse<TestResponseDTO.Testing> test() throws Exception {
        // 성공 응답 코드 설정

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        //테스트 메시지를 DTO로 변환하여 성공 응답 생성 후 반환
        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDTO("This is Test!")
        );
    }

    // 예외 상황
    @GetMapping("/exception")
    //flag를 파라미터로 받아 검증, 그 결과에 따라 응답을 반환
    public ApiResponse<TestResponseDTO.Exception> exception(
            @RequestParam Long flag
    ) {

        //flag 검증 로직 호출
        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDTO("This is Test!"));
    }
}