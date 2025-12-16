package com.example.umc9th.app.domain.member.repository;

import com.example.umc9th.app.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//JPA가 기본으로 제공하는 기능만 사용해도 되면 별도의 쿼리를 작성할 필요 없음
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
   Optional<Member> findByEmail(String email);
}
