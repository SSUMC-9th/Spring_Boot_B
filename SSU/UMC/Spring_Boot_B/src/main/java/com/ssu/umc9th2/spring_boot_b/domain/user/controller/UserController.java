package com.ssu.umc9th2.spring_boot_b.domain.user.controller;

import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionStatusResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserPageResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public GetUserPageResponse getUserPage(@PathVariable Long userId) {
        return userService.getUserPage(userId);
    }

    @GetMapping("/mission/status/{userId}")
    public List<GetUserMissionStatusResponse> getUserMissionStatus(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return userService.getUserMissionStatus(userId,page,size).getContent();
    }

}
