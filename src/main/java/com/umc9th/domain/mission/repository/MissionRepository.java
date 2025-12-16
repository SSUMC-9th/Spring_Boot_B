package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.UserMissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page; // 💡 페이징 처리를 위해 Page 클래스 사용
import org.springframework.data.domain.Pageable; // 💡 페이징 정보를 받기 위해 Pageable 사용
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    //내가 진행중/완료한 미션 모아보기 쿼리 (페이징 포함)
    @Query("SELECT new com.example.umc9th.domain.mission.dto.UserMissionDto(" +
            "um.id, " +
            "m.id, " +
            "s.storeName, " +
            "m.title, " +
            "m.content, " + // Mission 엔티티 필드 이름이 content
            "m.point, " +   // Mission 엔티티 필드 이름이 point
            "um.status, " +
            "m.deadline, " +
            "um.createdAt) " +
            "FROM UserMission um " + // UserMission 엔티티를 주체로 시작
            "JOIN um.mission m " +   // UserMission(N):Mission(1) 조인
            "JOIN m.store s " +      // Mission(ManyToOne) -> Store(One) 조인
            "WHERE um.user.id = :userId " +
            // SQL의 um.status IN ('진행중', '성공')은 Boolean status 필드로 대체
            // JPA에서 Enum/String 대신 Boolean 상태를 사용한다 가정 (status 필드가 Boolean)
            "ORDER BY um.createdAt DESC")
    // List 대신 Page<DTO>를 반환하여 페이징을 처리하고, Pageable 객체를 인자로
    //Page는 데이터 외에 전체 개수, 총 페이지 수 등 페이지네이션 메타 정보를 함께 반환
    Page<UserMissionDto> findUserMissionsByUserId(@Param("userId") Long userId, Pageable pageable);
    // 가게 id로 mission 검색
    Page<Mission> findByStoreId(Long storeId, Pageable pageable);
}