package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.dto.DesignerLicenseImageDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileRequest;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.service.DesignerRegisterService;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/designer")
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
    public BaseResponse<Object> registerDesignerProfile(@RequestBody DesignerProfileRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        designerRegisterService.registerDesignerProfile(request, user);
        return BaseResponse.success();
    }

    // 디자이너 자격증 사진 등록
    @PostMapping("/profile/image")
    public BaseResponse<Object> registerLicenseImage(@RequestBody DesignerLicenseImageDto request, @AuthenticationPrincipal UserDetails userDetails){
        User user = userFindService.findByUserDetails(userDetails);
        designerRegisterService.registerLicenseImage(request, user);
        return BaseResponse.success();
    }

}
