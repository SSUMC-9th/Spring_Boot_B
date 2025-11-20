package com.ssu.umc9th2.spring_boot_b.domain.user.repository;

import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetAvailableUserMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionStatusResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query("""
       SELECT new com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionStatusResponse(
                           m.id,
                           m.content,
                           m.point,
                           r.name,
                           um.isCompleted
       )
       FROM UserMission um JOIN Mission m ON um.mission.id = m.id JOIN Restaurant r ON r.id = m.restaurant.id
       WHERE um.user.id = :userId
       ORDER BY um.updatedAt DESC
      
        """)
    Page<GetUserMissionStatusResponse> getUserMissionStatus(@Param("userId") Long userId, Pageable pageable);

    @Query("""
       SELECT new com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetAvailableUserMissionResponse(
                           m.id,
                           m.content,
                           m.point,
                           r.name,
                           r.category,
                           m.deadline                           
       )
       FROM UserMission um JOIN Mission m ON um.mission.id = m.id JOIN Restaurant r ON r.id = m.restaurant.id
       WHERE um.user.id = :userId AND m.deadline > :now
       ORDER BY um.updatedAt DESC
    """)
    Page<GetAvailableUserMissionResponse> getAvailableUserMission(@Param("userId") Long userId, Pageable pageable, @Param("now") LocalDateTime now);
    Boolean existsByUserAndMission(User user, Mission mission);
}

