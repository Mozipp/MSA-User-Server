package com.mozipp.server.domain.model.controller;

import com.mozipp.server.domain.model.dto.ModelPetImageDto;
import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.model.dto.PetProfileRequest;
import com.mozipp.server.domain.model.service.ModelRegisterService;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/model")
public class ModelRegisterController {

    private final ModelRegisterService modelRegisterService;
    private final UserFindService userFindService;

    // Model 회원가입
    @PostMapping("/sign-up")
    public BaseResponse<Object> signUp(@RequestBody ModelSignUpDto modelSignUpDto){
        modelRegisterService.signUp(modelSignUpDto);
        return BaseResponse.success();
    }

    // Model 애완동물 프로필 등록
    @PostMapping("/pet/profile")
    public BaseResponse<Object> registerModelPetProfile(@RequestBody PetProfileRequest request, @AuthenticationPrincipal Long modelId) {
        modelRegisterService.registerModelPetProfile(request, modelId);
        return BaseResponse.success();
    }

    // Model 애완동물 사진 등록
    @PostMapping("/pet/petImage")
    public BaseResponse<Object> registerModelPetImage(ModelPetImageDto request, @AuthenticationPrincipal Long modelId){
        modelRegisterService.registerPetImage(request, modelId);
        return BaseResponse.success();
    }
}
