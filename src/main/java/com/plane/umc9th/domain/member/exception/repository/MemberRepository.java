package com.plane.umc9th.domain.member.exception.repository;

import com.plane.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m.point FROM Member m WHERE m.id=:memberId")
    int getPointByMemberId(@Param("memberId") Long memberId);

    Optional<Member> findByEmail(String email);
}
