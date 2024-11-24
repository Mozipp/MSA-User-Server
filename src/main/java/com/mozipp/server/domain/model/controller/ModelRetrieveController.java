package com.mozipp.server.domain.model.controller;

import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.model.repository.ModelRepository;
import com.mozipp.server.domain.model.service.ModelRetrieveService;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponse;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/model")
public class ModelRetrieveController {

    private final ModelRetrieveService modelRetrieveService;
    private final UserFindService userFindService;

    // Model 프로필 조회
    @GetMapping("/profile")
    public BaseResponse<ModelProfileResponse> getModelProfile(@RequestHeader("Authorization") String authorizationHeader) {
        Long modelId = userFindService.getUserId(authorizationHeader);
        ModelProfileResponse response = modelRetrieveService.getModelProfile(modelId);
        return BaseResponse.success(response);
    }

    // Model 애완동물 프로필 조회
    @GetMapping("/pet/profile")
    public BaseResponse<PetProfileResponse> getPetProfile(@RequestHeader("Authorization") String authorizationHeader) {
        Long modelId = userFindService.getUserId(authorizationHeader);
        PetProfileResponse response = modelRetrieveService.getPetProfile(modelId);
        return BaseResponse.success(response);
    }
}
