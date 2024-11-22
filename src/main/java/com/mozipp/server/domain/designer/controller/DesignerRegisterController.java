package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.dto.DesignerLicenseImageDto;
import com.mozipp.server.domain.designer.dto.DesignerPetGroomingImageDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileRequest;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.service.DesignerRegisterService;
import com.mozipp.server.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/designer")
public class DesignerRegisterController {

    private final DesignerRegisterService designerRegisterService;

    // 디자이너 회원가입
    @PostMapping("/sign-up")
    public BaseResponse<Object> signUp(@RequestBody DesignerSignUpDto request) {
        designerRegisterService.signUp(request);
        return BaseResponse.success();
    }

    //디자이너 기본 프로필 등록
    @PostMapping("/profile")
    public BaseResponse<Object> registerDesignerProfile(@RequestBody DesignerProfileRequest request) {
        designerRegisterService.registerDesignerProfile(request);
        return BaseResponse.success();
    }

    // 디자이너 자격증 사진 등록
    @PostMapping("/profile/licenseImage")
    public BaseResponse<Object> registerLicenseImage(DesignerLicenseImageDto request){
        designerRegisterService.registerLicenseImage(request);
        return BaseResponse.success();
    }

    // 디자이너 PetGrooming 사진 등록
    @PostMapping("/profile/petGroomingImage")
    public BaseResponse<Object> registerPetGroomingImage(DesignerPetGroomingImageDto request) {
        designerRegisterService.registerPetGroomingImage(request);
        return BaseResponse.success();
    }


}
