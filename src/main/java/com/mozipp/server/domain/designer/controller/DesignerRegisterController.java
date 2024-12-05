package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.dto.DesignerLicenseImageDto;
import com.mozipp.server.domain.designer.dto.DesignerPetGroomingImageDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileRequest;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.service.DesignerRegisterService;
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
@RequestMapping("/api/users/designer")
public class DesignerRegisterController {

    private final DesignerRegisterService designerRegisterService;
    private final UserFindService userFindService;

    // 디자이너 회원가입
    @PostMapping("/sign-up")
    public BaseResponse<Object> signUp(@RequestBody DesignerSignUpDto request) {
        designerRegisterService.signUp(request);
        return BaseResponse.success();
    }

    //디자이너 기본 프로필 등록
    @PostMapping("/profile")
    public BaseResponse<Object> registerDesignerProfile(@RequestBody DesignerProfileRequest request, @AuthenticationPrincipal Long designerId) {
        designerRegisterService.registerDesignerProfile(request, designerId);
        return BaseResponse.success();
    }

    // 디자이너 자격증 사진 등록
    @PostMapping("/profile/licenseImage")
    public BaseResponse<Object> registerLicenseImage(DesignerLicenseImageDto request, @AuthenticationPrincipal Long designerId){
        designerRegisterService.registerLicenseImage(request, designerId);
        return BaseResponse.success();
    }

    // 디자이너 PetGrooming 사진 등록
    @PostMapping("/profile/petGroomingImage")
    public BaseResponse<Object> registerPetGroomingImage(DesignerPetGroomingImageDto request, @AuthenticationPrincipal Long designerId) {
        designerRegisterService.registerPetGroomingImage(request, designerId);
        return BaseResponse.success();
    }
}
