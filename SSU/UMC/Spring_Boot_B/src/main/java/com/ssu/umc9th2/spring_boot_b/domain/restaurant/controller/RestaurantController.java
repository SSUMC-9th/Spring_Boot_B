package com.ssu.umc9th2.spring_boot_b.domain.restaurant.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantListResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<GetRestaurantListResponse>> getRestaurantListWithFilter(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String name,
            @RequestParam String orderCondition,
            @RequestParam Integer size,
            @RequestParam Integer page
    ){
        GetRestaurantListResponse response = restaurantService.getRestaurantListWithFilter(region, name, orderCondition, size, page);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, response);
    }

}
