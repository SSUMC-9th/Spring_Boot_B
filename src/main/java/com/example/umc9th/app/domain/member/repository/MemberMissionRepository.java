package com.example.umc9th.app.domain.member.repository;

import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;

import com.example.umc9th.app.domain.member.dto.MemberMissionResponse;
import com.example.umc9th.app.domain.member.entity.Member;
import com.example.umc9th.app.domain.member.entity.MemberMission;
import com.example.umc9th.app.domain.mission.entity.Mission;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


//데이터 접근을 담당하는 JPA Repository
//정적 쿼리인 JPQL을 사용함
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    //직접 문자열 뭐리를 명시 - 어노테이션 사용
    //dto로 직접 매핑 -> dto 생성자 호출 -> 바로 dto 생성
    //where에서 파라미터로 받은 memberId, status가 바인딩됨 - 컴파일 시점에는 오류가 발생해도 알 수 없음(JPQL 한계)
    @Query("""
        select new com.example.umc9th.app.domain.member.dto.MemberMissionResponse(
            mm.id,
            mm.mission.reward,
            mm.mission.cashRequirement,
            mm.mission.store.name,
            mm.memberMissionStatus
        )
            from MemberMission mm
            join mm.mission m
            join m.store s
            where mm.member.id = :memberId and mm.memberMissionStatus = :status
            order by mm.updatedAt desc, mm.id desc
    """)
    //위의 쿼리 결과를 조뢰하기 위한 메서드 시그니처: 무엇을 조회하고 어떻게 받고 어떤 형식으로 돌려줄지 -> 이걸 보고 JPA가 Query를 알맞게 해석
    Page<MemberMissionResponse> getMemberMissions(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,

            //페이징 기능 제어 객체(JPA가 자동으로 Page 역할 부여)
            Pageable pageable
    );

    @Query("""
select new com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse(
    m.id,
    s.foodCategory.name,
    m.reward,
    m.cashRequirement,
    s.name,
    m.dueDate
)
from MemberMission mm
join mm.mission m
join m.store s
where mm.member.id = :memberId
  and mm.memberMissionStatus = 'IN_PROGRESS'
order by m.dueDate asc, mm.id desc
""")
    Page<GetAvailableMemberMissionResponse> getAvailableMemberMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);
    boolean existsByMemberAndMission(Member member, Mission mission);
}
