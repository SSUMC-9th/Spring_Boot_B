package com.ssu.umc9th2.spring_boot_b.domain.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.ssu.umc9th2.spring_boot_b.domain.mission.entity.QMission.mission;
import static com.ssu.umc9th2.spring_boot_b.domain.user.entity.QUserMission.userMission;
import static com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.QRestaurant.restaurant;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionCustomRepositoryImpl implements UserMissionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<GetUserMissionResponse> findUserMissionList(Long userId, Pageable pageable, Boolean isCompleted) {
        List<GetUserMissionResponse> content = jpaQueryFactory
                .select(Projections.constructor(
                        GetUserMissionResponse.class,
                        mission.id,
                        mission.content,
                        mission.point,
                        restaurant.name,
                        userMission.isCompleted
                ))
                .from(userMission)
                .join(userMission.mission, mission)
                .join(mission.restaurant, restaurant)
                .where(
                        (userMission.user.id.eq(userId).and(userMission.isCompleted.eq(isCompleted))
                        )

                )
                .orderBy(userMission.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(userMission.count())
                .from(userMission)
                .where(userMission.user.id.eq(userId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);


    }
}
