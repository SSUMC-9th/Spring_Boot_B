package com.example.umc9th.app.domain.store.controller;

import com.example.umc9th.app.domain.store.dto.*;
import com.example.umc9th.app.domain.store.service.StoreQueryService;
import com.example.umc9th.app.domain.store.service.StoreService;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.infra.validator.ValidPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController implements StoreControllerDocs {
    private final StoreService storeService;
    private final StoreQueryService storeQueryService;

    @PostMapping("")
    public ApiResponse<PostCreateStoreResponse.CreateStoreDTO> createStore(@RequestBody @Valid PostCreateStoreRequest.CreateStoreDTO dto) {
        PostCreateStoreResponse.CreateStoreDTO newStore = storeService.createStore(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, newStore);
    }

    @PostMapping("/mission")
    public ApiResponse<PostCreateStoreMissionResponse.CreateStoreMissionDTO> createStoreMission(@RequestBody @Valid PostCreateStoreMissionRequest.CreateStoreMissionDTO dto) {
        PostCreateStoreMissionResponse.CreateStoreMissionDTO newStoreMission = storeService.createStoreMission(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, newStoreMission);
    }

    @GetMapping("/{storeId}/mission")
    public ApiResponse<Page<GetStoreMissionResponse>> getStoreMission(@PathVariable Long storeId, @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());
        Page<GetStoreMissionResponse> dto = storeQueryService.getStoreMission(storeId, pageRequest);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, dto);
    }
}
