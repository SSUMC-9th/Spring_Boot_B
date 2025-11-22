package com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.querydsl.core.types.Projections.bean;
import static com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.QRestaurant.restaurant;
import static com.ssu.umc9th2.spring_boot_b.domain.location.entity.QLocation.location;
import static com.ssu.umc9th2.spring_boot_b.domain.mission.entity.QMission.mission;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GetRestaurantResponse> findAllWithFilter(String region, String name, String orderCondition, Integer size, Integer page) {
        return jpaQueryFactory
                .select(bean(GetRestaurantResponse.class,
                        restaurant.id.as("restaurantId"),
                        restaurant.name.as("name"),
                        restaurant.category.as("category"),
                        restaurant.openTime.as("openTime"),
                        restaurant.closeTime.as("closeTime"),
                        location.id.as("locationId"),
                        location.name.as("address")
                ))
                .from(restaurant)
                .leftJoin(restaurant.location, location)
                .where(
                        restaurantLocationEq(region),
                        restaurantNameContainsWords(name)
                )
                .orderBy(getOrderCondition(orderCondition))
                .offset((long) page * size)
                .limit(size)
                .fetch();
    }

    private BooleanExpression restaurantNameContainsWords(String keyword) {
        if (keyword == null) return null;
        String trimmed = keyword.trim();
        if (trimmed.isEmpty()) return null; // ← ★ 핵심

        String[] words = trimmed.split("\\s+");
        BooleanExpression condition = null;

        for (String word : words) {
            if (word.isBlank()) continue;
            BooleanExpression wordCondition = restaurant.name.containsIgnoreCase(word);
            condition = (condition == null) ? wordCondition : condition.or(wordCondition);
        }

        return condition;
    }

    @Override
    public Page<GetRestaurantMissionResponse> findRestaurantMissionList(Long restaurantId, Pageable pageable) {
        List<GetRestaurantMissionResponse> content = jpaQueryFactory
                .select(Projections.constructor(
                        GetRestaurantMissionResponse.class,
                        mission.id,
                        mission.content,
                        mission.point,
                        mission.deadline,
                        restaurant.name,
                        restaurant.category,
                        location.name
                ))
                .from(restaurant)
                .where(restaurant.id.eq(restaurantId))
                .join(restaurant.location, location)
                .leftJoin(restaurant.missions, mission)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(restaurant.count())
                .from(restaurant)
                .where(restaurant.id.eq(restaurantId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression restaurantLocationEq(String locationName) {
        if (locationName == null || locationName.isBlank()) return null;
        return restaurant.location.name.containsIgnoreCase(locationName.trim());
    }

    private OrderSpecifier<?> getOrderCondition(String orderCondition) {
        if ("latest".equalsIgnoreCase(orderCondition)) {
            return restaurant.createdAt.desc();  // 최신순
        } else if ("name".equalsIgnoreCase(orderCondition)) {
            // 정렬 우선순위: 한글 > 영어 대문자 > 영어 소문자 > 특수문자
            return Expressions.stringPath("name").asc();
        }
        return restaurant.id.desc(); // 기본값
    }

}
