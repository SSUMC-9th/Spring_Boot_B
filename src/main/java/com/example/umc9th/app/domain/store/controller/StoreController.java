package com.example.umc9th.app.domain.store.controller;

import com.example.umc9th.app.domain.store.dto.PostCreateStoreMissionRequest;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreMissionResponse;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreRequest;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreResponse;
import com.example.umc9th.app.domain.store.service.StoreService;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("")
    public ApiResponse<PostCreateStoreResponse.CreateStoreDTO> createStore(
            @RequestBody @Valid PostCreateStoreRequest.CreateStoreDTO dto
    ) {
        PostCreateStoreResponse.CreateStoreDTO newStore = storeService.createStore(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, newStore);
    }
    @PostMapping("/mission")
    public ApiResponse<PostCreateStoreMissionResponse.CreateStoreMissionDTO> createStoreMission(
            @RequestBody @Valid PostCreateStoreMissionRequest.CreateStoreMissionDTO dto
    ) {
        PostCreateStoreMissionResponse.CreateStoreMissionDTO newStoreMission = storeService.createStoreMission(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, newStoreMission);
    }
}
