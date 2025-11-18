package com.example.umc9th.app.domain.member.service;


import com.example.umc9th.app.domain.member.dto.*;
import com.example.umc9th.app.domain.member.entity.Member;
import com.example.umc9th.app.domain.member.entity.MemberFoodCategory;
import com.example.umc9th.app.domain.member.entity.MemberMission;
import com.example.umc9th.app.domain.member.exception.MemberException;
import com.example.umc9th.app.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.app.domain.member.repository.MemberFoodCategoryRepository;
import com.example.umc9th.app.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.app.domain.member.repository.MemberRepository;
import com.example.umc9th.app.domain.mission.entity.Mission;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.app.domain.mission.exception.MissionErrorCode;
import com.example.umc9th.app.domain.mission.exception.MissionException;
import com.example.umc9th.app.domain.mission.repository.MissionRepository;
import com.example.umc9th.infra.exception.FoodErrorCode;
import com.example.umc9th.infra.exception.FoodException;
import com.example.umc9th.infra.repository.FoodCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.umc9th.app.domain.mission.enums.MemberMissionStatus.IN_PROGRESS;
import static com.example.umc9th.app.domain.mission.exception.MissionErrorCode.NOT_FOUND;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionsRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberFoodCategoryRepository memberFoodCategoryRepository;

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

    @Transactional
    public PostCreateMemberResponse.JoinDTO createMember(PostCreateMemberRequest.JoinDTO dto) {
        List<MemberFoodCategory> memberFoodList = new ArrayList<>();
        Member newMember = Member.builder()
                .gender(dto.gender())
                .birth(dto.birth())
                .name(dto.name())
                .email(dto.email())
                .build();
        Member saved = memberRepository.save(newMember);
        if (dto.foodCategories() != null && !dto.foodCategories().isEmpty()) {
            List<MemberFoodCategory> memberFoodCategories = dto.foodCategories().stream()
                    .map(id -> MemberFoodCategory.builder()
                            .member(saved)
                            .foodCategory(foodCategoryRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            memberFoodCategoryRepository.saveAll(memberFoodCategories);
        }

        return new PostCreateMemberResponse.JoinDTO(
                saved.getId(),
                saved.getCreatedAt()
        );
    }

    @Transactional

    public PostCreateMemberMissionResponse.DTO challenge(Long missionId, Long memberId) {
        log.info("[challenge] missionId = {}, memberId = {}", missionId, memberId);
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if (memberMissionsRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionException(MissionErrorCode.ALREADY_DONE);
        }

        MemberMission memberMission = MemberMission.builder()
                .mission(mission)
                .member(member)
                .memberMissionStatus(IN_PROGRESS)
                .build();

        MemberMission saved = memberMissionsRepository.save(memberMission);

        return new PostCreateMemberMissionResponse.DTO(saved.getId());
    }
}
