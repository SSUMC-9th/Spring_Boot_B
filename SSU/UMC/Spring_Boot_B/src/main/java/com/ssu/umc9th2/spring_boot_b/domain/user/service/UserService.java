package com.ssu.umc9th2.spring_boot_b.domain.user.service;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import com.ssu.umc9th2.spring_boot_b.domain.mission.service.MissionService;
import com.ssu.umc9th2.spring_boot_b.domain.review.repository.ReviewCustomRepositoryImpl;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.*;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.UserMission;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserMissionRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final ReviewCustomRepositoryImpl reviewCustomRepositoryImpl;
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

    public GetUserReviewListResponse getUserReviewList(Long userId,String restaurantName, Double rating) {
        getUserByUserId(userId);
        List<GetUserReviewResponse> userReviewList = reviewCustomRepositoryImpl.findAllByUserWithFilter(userId, restaurantName, rating);
        return new GetUserReviewListResponse(userReviewList);
    }

    public Page<GetUserMissionStatusResponse> getUserMissionStatus(Long userId, int page, int size) {
        getUserByUserId(userId);
        return userMissionRepository.getUserMissionStatus(userId, PageRequest.of(page, size));
    }

    public Page<GetAvailableUserMissionResponse> getAvailableUserMission(Long userId, int page, int size) {
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

}
