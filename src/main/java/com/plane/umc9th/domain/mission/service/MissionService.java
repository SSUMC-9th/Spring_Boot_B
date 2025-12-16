package com.plane.umc9th.domain.mission.service;

import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.member.exception.repository.MemberRepository;
import com.plane.umc9th.domain.mission.converter.MissionConverter;
import com.plane.umc9th.domain.mission.dto.MissionReqDTO;
import com.plane.umc9th.domain.mission.dto.MissionResDTO;
import com.plane.umc9th.domain.mission.entity.Mission;
import com.plane.umc9th.domain.mission.repository.MissionRepository;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.plane.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.plane.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.MissionPreViewListDTO getMyMissions(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missions = this.missionRepository.findMyMissions(memberId, pageable);
        return MissionConverter.toMissionPreviewListDTO(missions);
    }

    public MissionResDTO.MissionPreViewListDTO getStoreMissions(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missions = missionRepository.findByRestaurantId(userId, pageable);
        return MissionConverter.toMissionPreviewListDTO(missions);
    }

    public void createMission(MissionReqDTO.CreateMissionDTO dto) {
        Restaurant restaurant = this.restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(()->new GeneralException(GeneralErrorCode.NOT_FOUND));
        Member member = this.memberRepository.findById(dto.memberId()).orElse(null);

        Mission mission = Mission.builder()
                .member(member)
                .restaurant(restaurant)
                .description(dto.description())
                .point(dto.point())
                .deadline(dto.deadline())
                .minAmount(dto.minAmount())
                .point(dto.point())
                .build();
        this.missionRepository.save(mission);
    }
}
