package com.ssu.umc9th2.spring_boot_b.domain.home.controller;

import com.ssu.umc9th2.spring_boot_b.domain.home.dto.response.GetHomeResponse;
import com.ssu.umc9th2.spring_boot_b.domain.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/{userId}")
    public GetHomeResponse getAvailableUserMission(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return homeService.getHome(userId, page, size);
    }
}
