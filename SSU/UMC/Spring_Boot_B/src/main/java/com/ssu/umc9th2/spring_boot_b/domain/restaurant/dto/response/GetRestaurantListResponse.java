package com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantListResponse {
    private List<GetRestaurantResponse> restaurantList;
}
