package com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantResponse {
    private Long restaurantId;
    private String name;
    private String category;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Long locationId;
    private String address;
}
