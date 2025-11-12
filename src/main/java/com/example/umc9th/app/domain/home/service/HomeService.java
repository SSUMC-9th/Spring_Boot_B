package com.example.umc9th.app.domain.home.service;

import com.example.umc9th.app.domain.home.dto.GetHomeResponse;
import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberHomeResponse;
import com.example.umc9th.app.domain.member.service.MemberService;
import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.app.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final MemberService memberService;


    public GetHomeResponse getHome(Long memberId, int page, int size) {
        List<GetAvailableMemberMissionResponse> missions = memberService
                .getAvailableMemberMission(memberId, page, size)
                .getContent();


        GetMemberHomeResponse member = memberService.getMemberHome(memberId);
        return new GetHomeResponse(member, missions);
    }
}
