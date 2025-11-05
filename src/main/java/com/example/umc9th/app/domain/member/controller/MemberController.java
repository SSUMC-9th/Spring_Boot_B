package com.example.umc9th.app.domain.member.controller;

import com.example.umc9th.app.domain.member.dto.GetMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberMyPageResponse;
import com.example.umc9th.app.domain.member.service.MemberService;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
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
    public GetMemberMyPageResponse memberMyPage(@PathVariable Long memberId) {
        return memberService.memberMyPage(memberId);
    }

    @GetMapping("/{memberId}/missions")
    public List<GetMemberMissionResponse> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam MemberMissionStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Pageable pageable) {
        return memberService.getMemberMissions(memberId,status,page,size).getContent();
    }
}
