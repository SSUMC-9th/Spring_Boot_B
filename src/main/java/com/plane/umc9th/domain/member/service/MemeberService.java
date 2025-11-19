package com.plane.umc9th.domain.member.service;

import com.plane.umc9th.domain.member.dto.HomeResponseDTO;
import com.plane.umc9th.domain.member.dto.MyPageResponseDTO;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.member.repository.MemberRepository;
import com.plane.umc9th.domain.mission.entity.Mission;
import com.plane.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemeberService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    MyPageResponseDTO getMyPageInfo(Long member_id) {
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO();
        myPageResponseDTO.setEmail(member.getEmail());
        myPageResponseDTO.setName(member.getName());
        myPageResponseDTO.setPoint(member.getPoint());
        myPageResponseDTO.setIsVerified(member.isVerified());

        return myPageResponseDTO;
    }

    HomeResponseDTO getHomeInfo(Long member_id) {
        HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
        int completedCount = missionRepository.getCompletedCountByMemberId(member_id) % 10;
        List<Mission> missions = missionRepository.findActiveMissionsByMemeberId(member_id);
        int point = memberRepository.getPointByMemberId(member_id);
        homeResponseDTO.setMissions(missions);
        homeResponseDTO.setCompleteMissionCount(completedCount);
        homeResponseDTO.setPoint(point);

        return homeResponseDTO;
    }
}
