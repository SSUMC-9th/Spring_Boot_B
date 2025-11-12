package com.example.umc9th.app.domain.member.repository;

import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberMissionResponse;
import com.example.umc9th.app.domain.member.entity.MemberMission;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("""
        select new com.example.umc9th.app.domain.member.dto.GetMemberMissionResponse(
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
    Page<GetMemberMissionResponse> getMemberMissions(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,
            Pageable pageable
    );

    @Query("""
select new com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse(
    m.id,
    s.foodCategory.name,
    m.reward,
    m.cashRequirement,
    s.name,
    mm.dueDate
)
from MemberMission mm
join mm.mission m
join m.store s
where mm.member.id = :memberId
  and mm.memberMissionStatus = com.example.umc9th.app.domain.mission.enums.MemberMissionStatus.READY
order by mm.dueDate asc, mm.id desc
""")
    Page<GetAvailableMemberMissionResponse> getAvailableMemberMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );


}
