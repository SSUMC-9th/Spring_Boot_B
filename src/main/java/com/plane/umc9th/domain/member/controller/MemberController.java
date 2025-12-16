package com.plane.umc9th.domain.member.controller;

import com.plane.umc9th.domain.member.dto.MemberReqDTO;
import com.plane.umc9th.domain.member.dto.MemberResDTO;
import com.plane.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.plane.umc9th.domain.member.service.command.MemberCommandService;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }
}
