package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.req.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.res.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.UserMission;
import com.example.umc9th.domain.user.repository.MyPageRepository; // 혹은 UserRepository
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MyPageRepository userRepository;

    public MissionResponseDto.ChallengeResultDto challengeMission(Long missionId, Long userId, MissionRequestDto.ChallengeRequest request) {

// 1. 가짜 미션 생성
        Mission mockMission = Mission.builder()
                .id(missionId)
                .title("테스트 미션")
                .point(500L)
                .build();

        // 2. 가짜 유저 생성
        User mockUser = User.builder()
                .id(userId)
                .build();

        // 3. 가짜 UserMission 생성 (저장 안 함)
        UserMission mockUserMission = UserMission.builder()
                .id(1L) // 임의의 ID
                .user(mockUser)
                .mission(mockMission)
                .status(true) // 도전 중
                .build();

        // 4. 결과 반환 (Converter 사용)

        return MissionResponseDto.ChallengeResultDto.builder()
                .userMissionId(1L)
                .missionId(missionId)
                .missionTitle("테스트 미션")
                .status("도전 중")
                .createdAt(LocalDateTime.now())
                .build();
     /*  // 1. 미션 조회
     Mission mission = missionRepository.findById(missionId)
              .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. 유저 조회
       User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

      // 3. 이미 도전 중인지 확인 (중복 방지)
      if (userMissionRepository.existsByUserIdAndMissionId(userId, missionId)) {
           // 이미 도전 중이면 에러 발생 (BAD_REQUEST 등 적절한 에러코드 사용)
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

       // 4. 저장 (도전 시작)
       UserMission userMission = MissionConverter.toUserMission(user, mission);
       UserMission savedUserMission = userMissionRepository.save(userMission);

       // 5. 결과 반환
      return MissionConverter.toChallengeResultDto(savedUserMission);*/
    }
}