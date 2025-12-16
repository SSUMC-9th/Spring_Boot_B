package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.review.dto.UserReviewDto; // 💡 사용자 리뷰 목록 DTO
import com.example.umc9th.domain.user.dto.MyPageUserDto; // 💡 마이 페이지 상단 DTO
import com.example.umc9th.domain.user.entity.User; // User 엔티티 import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MyPageRepository extends JpaRepository<User, Long> {

    // [1. 마이 페이지 상단 정보 조회 쿼리]
    // 마이 페이지 화면 (닉네임, 포인트, 리뷰 개수 등)에 필요한 데이터를 조회
    @Query("SELECT new com.example.umc9th.domain.user.dto.MyPageUserDto(" +
            "u.name, " +
            "u.address, " +
            "u.userPoint, " +
            "COUNT(r.id)) " +     // 사용자가 작성한 리뷰 개수
            "FROM User u " +
            "LEFT JOIN Review r ON r.user = u " +
            "WHERE u.id = :userId " +
            "GROUP BY u.id, u.name, u.address, u.userPoint")
    Optional<MyPageUserDto> findMyPageDetailsByUserId(@Param("userId") Long userId);


    //2. 사용자가 작성한 리뷰 목록 조회 쿼리
    // '작성한 리뷰' 버튼 클릭 시 호출되어, page로 반환
    @Query("SELECT new com.example.umc9th.domain.review.dto.UserReviewDto(" +
            "r.id, " +
            "s.storeName, " +   // Store 정보를 가져옴
            "r.star, " +
            "r.content, " +
            "r.createdAt) " +
            "FROM Review r " +
            "JOIN r.store s " + // Review(ManyToOne) -> Store(One) 조인
            "WHERE r.user.id = :userId " + // 해당 User가 작성한 리뷰 필터링
            "ORDER BY r.createdAt DESC")
    Page<UserReviewDto> findAllUserReviewsByUserId(@Param("userId") Long userId, Pageable pageable);
}