package com.example.umc9th.app.domain.member.controller;

import com.example.umc9th.app.domain.member.dto.*;
import com.example.umc9th.app.domain.member.service.MemberService;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<GetMemberMyPageResponse> memberMyPage(@PathVariable Long memberId) {
        GetMemberMyPageResponse dto = memberService.memberMyPage(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);

    }

    @GetMapping("/{memberId}/missions")
    public ApiResponse<List<GetMemberMissionResponse>> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam MemberMissionStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Pageable pageable) {
        //getContent()로 실제 list 데이터만 반환(Page<GetMemberMissionResponse> 객체에서 추출)
        List<GetMemberMissionResponse> dto = memberService.getMemberMissions(memberId, status, page, size).getContent();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }

    @PostMapping("/sign-up")
    public ApiResponse<PostCreateMemberResponse.JoinDTO> signUp(
            @RequestBody @Valid PostCreateMemberRequest.JoinDTO dto
    ) {
        PostCreateMemberResponse.JoinDTO newMember = memberService.createMember(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, newMember);
    }
    @PostMapping("/missions/challenge")
    public ApiResponse<PostCreateMemberMissionResponse.DTO> challengeMission(
            @RequestBody @Valid PostCreateMemberMissionRequest.DTO dto
    ) {
        PostCreateMemberMissionResponse.DTO response = memberService.challenge(dto.missionId(),dto.memberId());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
