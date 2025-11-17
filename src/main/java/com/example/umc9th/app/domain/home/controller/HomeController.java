package com.example.umc9th.app.domain.home.controller;

import com.example.umc9th.app.domain.home.dto.GetHomeResponse;
import com.example.umc9th.app.domain.home.service.HomeService;
import com.example.umc9th.app.domain.member.service.MemberService;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//프로트로부터 요청과 응답을 주고 받음
//RestAPI 요청 처리 컨트롤러
@RestController
//final 필드에 대해 생성자를 자동 생성하여 의존성 주입
@RequiredArgsConstructor
//컨트롤러의 기본 요청 경로 설정
@RequestMapping("/home")
public class HomeController {
    //실제 비즈니스 로직을 처리할 서비스 객체 할당
    private final HomeService homeService;
    //Get 요청 처리 + memberId 받음
    @GetMapping("/{memberId}")
    public ApiResponse<GetHomeResponse> getAvailableMemberMission(
            //매개변수로 memberId 받음
            @PathVariable Long memberId,
            //요청 파라미터
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        GetHomeResponse dto = homeService.getHome(memberId, page, size);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,dto);
    }
}

