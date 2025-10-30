package com.ssu.umc9th2.spring_boot_b.domain.notification.repository;

import com.ssu.umc9th2.spring_boot_b.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    int countByUserId(Long userId);
}
