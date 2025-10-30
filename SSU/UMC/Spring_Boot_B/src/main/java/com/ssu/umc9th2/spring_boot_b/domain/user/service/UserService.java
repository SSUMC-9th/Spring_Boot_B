package com.ssu.umc9th2.spring_boot_b.domain.user.service;

import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetAvailableUserMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionStatusResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserPageResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserSummaryResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserMissionRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

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

    public Page<GetUserMissionStatusResponse> getUserMissionStatus(Long userId, int page, int size) {
        return userMissionRepository.getUserMissionStatus(userId, PageRequest.of(page, size));
    }

    public Page<GetAvailableUserMissionResponse> getAvailableUserMission(Long userId, int page, int size) {
        return userMissionRepository.getAvailableUserMission(userId, PageRequest.of(page,size), LocalDateTime.now());
    }

    public GetUserSummaryResponse getUserSummary(Long userId,Integer notificationCount) {
        User user = getUserByUserId(userId);
        return new GetUserSummaryResponse(user.getNickname(), user.getEmail(), user.getPoint(),notificationCount);
    }

    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(null);
    }

}
