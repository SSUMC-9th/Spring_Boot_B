package com.umc9th.domain.review.repository;

import com.umc9th.domain.review.dto.ReviewDetailDto;
import com.umc9th.domain.review.entity.Review;
import com.umc9th.domain.store.entity.Store;
import com.umc9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // @Query를 사용하여 네이티브 SQL을 JPQL DTO Projection으로 리팩토링
    @Query("SELECT new com.umc9th.domain.review.dto.ReviewDetailDto(" +
            "r.id, " +
            "r.user.name, " +      // User 엔티티의 name 필드
            "r.star, " +
            "r.content, " +
            "r.createdAt, " +
            "rf.feedbackContent) " + // ReviewFeedback 엔티티의 feedbackContent 필드
            "FROM Review r " +
            "LEFT JOIN r.feedback rf " + // Review(1):ReviewFeedback(1) 관계 조인
            "WHERE r.store.id = :storeId " +
            "ORDER BY r.createdAt DESC")
    List<ReviewDetailDto> findReviewDetailsByStoreId(@Param("storeId") Long storeId);

    //
    @EntityGraph(attributePaths = {"user", "feedback"})
    List<Review> findByStoreId(Long storeId);
}