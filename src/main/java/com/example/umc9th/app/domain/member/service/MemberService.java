package com.example.umc9th.app.domain.member.service;


import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberHomeResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberMyPageResponse;
import com.example.umc9th.app.domain.member.entity.Member;
import com.example.umc9th.app.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.app.domain.member.repository.MemberRepository;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionsRepository;

    public GetMemberMyPageResponse memberMyPage(Long memberId) {
        Member member = findMemberById(memberId);

        return new GetMemberMyPageResponse(
                member.getName(),
                member.getEmail(),
                member.isPhoneNumVerified(),
                member.getPhoneNum(),
                member.getPoint()
                );
    }
    public Page<GetMemberMissionResponse> getMemberMissions(Long memberId, MemberMissionStatus status, int page, int size) {
            return memberMissionsRepository.getMemberMissions(memberId, status, PageRequest.of(page, size));
    }
    public Page<GetAvailableMemberMissionResponse> getAvailableMemberMission(Long memberId, int page, int size) {
        return memberMissionsRepository.getAvailableMemberMissions(memberId, PageRequest.of(page, size));
    }
    public GetMemberHomeResponse getMemberHome(Long memberId) {
        Member member = findMemberById(memberId);
        Long alarmCount = (long) member.getAlarms().size();
        return new GetMemberHomeResponse(member.getAddress(), alarmCount);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("member not found: " + memberId));
    }
}
