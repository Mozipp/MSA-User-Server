package com.mozipp.server.domain.model.controller;

import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.model.repository.ModelRepository;
import com.mozipp.server.domain.model.service.ModelRetrieveService;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponse;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/model")
public class ModelRetrieveController {

    private final ModelRetrieveService modelRetrieveService;
    private final ModelRepository modelRepository;

    // Model 프로필 조회
    @GetMapping("/profile/{modelId}")
    public BaseResponse<ModelProfileResponse> getModelProfile(@PathVariable Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        ModelProfileResponse response = modelRetrieveService.getModelProfile(model);
        return BaseResponse.success(response);
    }

    // Model 애완동물 프로필 조회
    @GetMapping("/pet/profile/{modelId}")
    public BaseResponse<PetProfileResponse> getPetProfile(@PathVariable Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        PetProfileResponse response = modelRetrieveService.getPetProfile(model);
        return BaseResponse.success(response);
    }
}
