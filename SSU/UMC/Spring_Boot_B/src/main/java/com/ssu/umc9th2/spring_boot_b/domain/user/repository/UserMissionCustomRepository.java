package com.ssu.umc9th2.spring_boot_b.domain.user.repository;

import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserMissionCustomRepository {
    Page<GetUserMissionResponse> findUserMissionList(Long userId, Pageable pageable,Boolean isCompleted);
}
