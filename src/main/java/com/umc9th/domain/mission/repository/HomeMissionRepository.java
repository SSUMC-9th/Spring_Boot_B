package com.umc9th.domain.mission.repository;

import com.umc9th.domain.mission.dto.HomeMissionDto; //HomeMissionDto 사용
import com.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// Mission 엔티티를 기반으로 작동
public interface HomeMissionRepository extends JpaRepository<Mission, Long> {

    //홈 화면: 현재 선택 지역에서 도전 가능한 미션 목록 조회 쿼리 (페이징 포함)
    @Query("SELECT new com.umc9th.domain.mission.dto.HomeMissionDto(" + // DTO 경로 변경
            "m.id, s.storeName, m.title, m.content, m.point, m.deadline) " +
            "FROM Mission m " +
            "JOIN m.store s " +             // Mission -> Store 조인
            "JOIN s.region r " +            // Store -> Region 조인
            // LEFT JOIN: 사용자가 해당 미션 기록(UserMission)을 가졌는지 확인
            "LEFT JOIN UserMission um ON um.mission = m AND um.user.id = :userId " +
            // WHERE: 지역 필터링 AND UserMission 기록이 NULL인 경우만 선택 (도전 가능)
            "WHERE r.id = :regionId AND um.id IS NULL " +
            "ORDER BY m.createdAt DESC")
    //메서드 이름도 DTO와 Repositor의 목적에 맞게 수정
    Page<HomeMissionDto> findAvailableMissionsInRegion(
            @Param("regionId") Long regionId,
            @Param("userId") Long userId,
            Pageable pageable);
}