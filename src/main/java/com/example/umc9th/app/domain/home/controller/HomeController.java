package com.example.umc9th.app.domain.home.controller;

import com.example.umc9th.app.domain.home.dto.GetHomeResponse;
import com.example.umc9th.app.domain.home.service.HomeService;
import com.example.umc9th.app.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

@GetMapping("/{memberId}")
public GetHomeResponse getAvailableMemberMission(
        @PathVariable Long memberId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    return homeService.getHome(memberId,page, size);
}
}

