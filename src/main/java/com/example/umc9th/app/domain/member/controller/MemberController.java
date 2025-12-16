package com.example.umc9th.app.domain.member.controller;

import com.example.umc9th.app.domain.member.dto.*;
import com.example.umc9th.app.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.app.domain.member.service.MemberQueryService;
import com.example.umc9th.app.domain.member.service.MemberService;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "회원")
public class MemberController {
    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    @GetMapping("/{memberId}")
    @Operation(summary = "마이페이지 정보", description = "마이페이지 정보를 가져옵니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "마이페이지 조회", content = @Content(schema = @Schema(implementation = GetMemberMyPageResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())
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
        PostCreateMemberMissionResponse.DTO response = memberService.challenge(dto.missionId(), dto.memberId());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @PostMapping("login")
    public ApiResponse<PostMemberLoginResponse.LoginDTO> login(
            @RequestBody @Valid PostMemberLoginRequest.LoginDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}
