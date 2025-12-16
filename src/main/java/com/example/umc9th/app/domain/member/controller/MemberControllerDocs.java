package com.example.umc9th.app.domain.member.controller;

import com.example.umc9th.app.domain.member.dto.*;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MemberControllerDocs {
    @Operation(summary = "마이페이지 정보", description = "마이페이지 정보를 가져옵니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "마이페이지 조회", content = @Content(schema = @Schema(implementation = GetMemberMyPageResponse.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<GetMemberMyPageResponse> memberMyPage(@PathVariable Long memberId);


    @Operation(summary = "나의 미션 정보 조회(페이징)", description = "나의 미션 정보를 조회합니다. status에 따라 조회가 가능합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "내 미션 조회 생성 성공", content = @Content(schema = @Schema(implementation = MemberMissionResponse.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<List<MemberMissionResponse>> getMemberMissions(@PathVariable Long memberId, @RequestParam MemberMissionStatus status, @ValidPage @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "회원 계정 생성", description = "회원 계정을 생성합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "계정 생성 성공", content = @Content(schema = @Schema(implementation = PostCreateMemberResponse.JoinDTO.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<PostCreateMemberResponse.JoinDTO> signUp(@RequestBody @Valid PostCreateMemberRequest.JoinDTO dto);

    @Operation(summary = "회원 미션 도전", description = "도전 중인 미션 목록에 새로운 미션을 추가합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "새로운 도전 중인 미션 추가 성공", content = @Content(schema = @Schema(implementation = PostCreateMemberMissionResponse.DTO.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<PostCreateMemberMissionResponse.DTO> challengeMission(@RequestBody @Valid PostCreateMemberMissionRequest.DTO dto);

    @Operation(summary = "회원 미션 완료", description = "미션을 완료되었음으로 수정합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 완료 수정 성공", content = @Content(schema = @Schema(implementation = MemberMissionResponse.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<MemberMissionResponse> updateMission(@PathVariable Long missionId, @RequestParam Long memberId);
}
