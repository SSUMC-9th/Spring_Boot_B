package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUser_IdAndMission_Id(Long userId, Long missionId);

    Page<UserMission> findByUser_IdAndStatus(Long userId, Boolean status, Pageable pageable);
}