package com.ssu.umc9th2.spring_boot_b.domain.home.service;

import com.ssu.umc9th2.spring_boot_b.domain.home.dto.response.GetHomeResponse;
import com.ssu.umc9th2.spring_boot_b.domain.notification.service.NotificationService;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetAvailableUserMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserSummaryResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HomeService {
    private final UserService userService;
    private final NotificationService notificationService;

    public GetHomeResponse getHome(Long userId, int page, int size) {
        List<GetAvailableUserMissionResponse> missions = userService
                .getAvailableUserMission(userId, page, size)
                .getContent();

        Integer notificationCount = notificationService.getNotificationCountByUserId(userId);
        GetUserSummaryResponse user = userService.getUserSummary(userId, notificationCount);
        return new GetHomeResponse(user, missions);
    }
}
