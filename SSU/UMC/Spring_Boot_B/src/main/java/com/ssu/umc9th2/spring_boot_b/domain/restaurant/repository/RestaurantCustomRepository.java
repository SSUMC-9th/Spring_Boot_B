package com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository;

import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantCustomRepository {
    List<GetRestaurantResponse> findAllWithFilter(String region, String name, String orderCondition, Integer size, Integer page);
    Page<GetRestaurantMissionResponse> findRestaurantMissionList(Long restaurantId, Pageable pageable);
}
