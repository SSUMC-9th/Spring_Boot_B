package com.ssu.umc9th2.spring_boot_b.domain.notification.service;

import com.ssu.umc9th2.spring_boot_b.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public int getNotificationCountByUserId(Long userId) {
        return notificationRepository.countByUserId(userId);
    }
}
