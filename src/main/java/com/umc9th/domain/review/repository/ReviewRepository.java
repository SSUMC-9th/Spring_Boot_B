package com.umc9th.domain.review.repository;

import com.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository와 Custom 인터페이스만 확장하는 가장 간결한 형태입니다.
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    // 기존의 모든 @Query 메서드는 삭제되었습니다.
}