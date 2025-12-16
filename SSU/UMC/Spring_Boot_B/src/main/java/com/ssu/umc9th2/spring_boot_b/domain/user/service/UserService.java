package com.ssu.umc9th2.spring_boot_b.domain.user.service;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import com.ssu.umc9th2.spring_boot_b.domain.mission.service.MissionService;
import com.ssu.umc9th2.spring_boot_b.domain.review.repository.ReviewCustomRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.*;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.UserMission;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserMissionCustomRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserMissionRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserMissionCustomRepository userMissionCustomRepository;
    private final ReviewCustomRepository reviewCustomRepository;
    private final MissionService missionService;

    public GetUserPageResponse getUserPage(Long userId) {
        User user = getUserByUserId(userId);

        return new GetUserPageResponse(
                user.getNickname(),
                user.getProfileLink(),
                user.getEmail(),
                user.getPhone(),
                user.getPoint()
        );
    }

    public Page<GetUserReviewResponse> getUserReviewList(Long userId, Pageable pageable) {
        return reviewCustomRepository.findUserReviewList(userId, pageable);
    }

    @Transactional
    public Page<GetUserMissionResponse> getUserMissionList(Long userId, Integer page, Integer size, Boolean isFinished) {
        return userMissionCustomRepository.findUserMissionList(userId, PageRequest.of(page-1, size),isFinished);
    }

    @Transactional
    public void updateUserMissionStatus(Long userId, Long missionId, Boolean status) {
        User user = getUserByUserId(userId);
        Mission mission = missionService.getMissionByMissionId(missionId);
        UserMission userMission = userMissionRepository.findByUserAndMission(user,mission);

        userMission.updateIsCompleted(status);
    }

    public Page<GetAvailableUserMissionResponse> getAvailableUserMission(Long userId, Integer page, Integer size) {
        return userMissionRepository.getAvailableUserMission(userId, PageRequest.of(page,size), LocalDateTime.now());
    }

    public GetUserSummaryResponse getUserSummary(Long userId,Integer notificationCount) {
        User user = getUserByUserId(userId);
        return new GetUserSummaryResponse(user.getNickname(), user.getEmail(), user.getPoint(),notificationCount);
    }

    public void createUserMission(Long userId, Long missionId) {
        User user = getUserByUserId(userId);
        Mission mission = missionService.getMissionByMissionId(missionId);

        if(isExistUserMission(user,mission))
            throw new GeneralException(ErrorStatus.USER_MISSION_ALREADY_EXIST);

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .isCompleted(false)
                .build();

        userMissionRepository.save(userMission);

    }

    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new GeneralException(ErrorStatus.USER_NOT_FOUND));
    }

    public boolean isExistUserMission(User user, Mission mission) {
        return userMissionRepository.existsByUserAndMission(user, mission);
    }

    // 이메일로 유저 찾기
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorStatus.EMAIL_NOT_FOUND));
    }

    public void updateRefreshToken(User user, String refreshToken) {
        user.updateRefreshToken(refreshToken);
    }

}
