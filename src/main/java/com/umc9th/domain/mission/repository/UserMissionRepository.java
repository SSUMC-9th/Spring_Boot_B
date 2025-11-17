package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.user.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 이 유저가 이 미션을 이미 가지고 있나 물어보는 메서드
    boolean existsByUserIdAndMissionId(Long userId, Long missionId);
}