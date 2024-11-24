package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.dto.*;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.designer.service.DesignerRegisterService;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponse;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<Object> registerDesignerProfile(@RequestBody DesignerProfileRequest request, @RequestHeader("Authorization") String authorizationHeader) {
        Long designerId = userFindService.getUserId(authorizationHeader);
        designerRegisterService.registerDesignerProfile(request, designerId);
        return BaseResponse.success();
    }

    // 디자이너 자격증 사진 등록
    @PostMapping("/profile/licenseImage")
    public BaseResponse<Object> registerLicenseImage(DesignerLicenseImageDto request, @RequestHeader("Authorization") String authorizationHeader){
        Long designerId = userFindService.getUserId(authorizationHeader);
        designerRegisterService.registerLicenseImage(request, designerId);
        return BaseResponse.success();
    }

    // 디자이너 PetGrooming 사진 등록
    @PostMapping("/profile/petGroomingImage")
    public BaseResponse<Object> registerPetGroomingImage(DesignerPetGroomingImageDto request, @RequestHeader("Authorization") String authorizationHeader) {
        Long designerId = userFindService.getUserId(authorizationHeader);
        designerRegisterService.registerPetGroomingImage(request, designerId);
        return BaseResponse.success();
    }
}
