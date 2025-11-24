package com.plane.umc9th.domain.mission.repository;

import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
    @Query("SELECT m FROM Mission m " +
            "WHERE m.member.id=:memberId AND m.isCompleted=false")
    public Page<Mission> findByMemeberId(@Param("memberId") Long memeberId, Pageable pageable);

    @Query("SELECT count(*) FROM Mission m " +
            "WHERE m.member.id=:memberId AND " +
            "m.isCompleted=true")
    int getCompletedCountByMemberId(@Param("memberId") Long memeberId);

    int countByMemberAndIsCompleted(Member member, boolean isCompleted);

    @Query("SELECT m FROM Mission m " +
            "WHERE m.member.id=:memberId AND " +
            "m.isCompleted=false AND " +
            "m.deadline>CURRENT_TIMESTAMP")
    public List<Mission> findActiveMissionsByMemeberId(@Param("memberId") Long memeberId);

    @Query("SELECT m FROM Mission m " +
            "WHERE m.member.id=:memberId AND " +
            "m.isCompleted=false AND " +
            "m.deadline>CURRENT_TIMESTAMP")
    public Page<Mission> findMyMissions(@Param("memberId") Long memeberId, Pageable pageable);

    @Query("SELECT m FROM Mission m " +
            "WHERE m.restaurant.id=:restaurantId AND " +
            "m.isCompleted=false AND " +
            "m.deadline>CURRENT_TIMESTAMP")
    public Page<Mission> findByRestaurantId(@Param("restaurantId") Long restaurantId, Pageable pageable);
}
