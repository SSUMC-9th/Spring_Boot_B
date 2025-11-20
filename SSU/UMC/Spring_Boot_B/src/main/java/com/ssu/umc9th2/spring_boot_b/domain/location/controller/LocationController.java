package com.ssu.umc9th2.spring_boot_b.domain.location.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.location.service.LocationService;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.request.CreateRestaurantRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
@Tag(name = "지역")
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/{locationId}/restaurants")
    @Operation(summary = "식당 추가", description = "특정 지역에 식당 추가")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201",description = "식당 추가 성공",content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저가 존재하지 않을 때", content =@Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "식당이 이미 존재할 때", content =@Content())
    public ResponseEntity<ApiResponse<Void>> createRestaurantInLocation(
            @PathVariable Long locationId,
            @RequestBody @Valid CreateRestaurantRequest request
    ) {
        locationService.createRestaurantInLocation(locationId, request);
        return ApiResponse.success(SuccessStatus.CREATE_RESTAURANT_SUCCESS);
    }
}
