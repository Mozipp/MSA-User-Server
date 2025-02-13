package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.dto.PetGroomingImageDto;
import com.mozipp.server.domain.designer.service.DesignerRetrieveService;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserMatchService;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponse;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import com.mozipp.server.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/designer")
public class DesignerRetrieveController {

    private final UserMatchService userMatchService;
    private final AuthService authService;
    private final DesignerRetrieveService designerRetrieveService;
    private final CookieUtil cookieUtil;

    // 디자이너 프로필 조회
    @GetMapping("/profile")
    public BaseResponse<DesignerProfileResponse> getDesignerProfile(@AuthenticationPrincipal Long designerId) {
        DesignerProfileResponse response = designerRetrieveService.getDesignerProfile(designerId);
        return BaseResponse.success(response);
    }

    @PostMapping("/login")
    public BaseResponse<Void> login(@Valid @RequestBody DesignerLoginDto loginDto, HttpServletResponse response) {
            User user = userMatchService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            if (user.getRole() == Role.MODEL) {
                throw new BaseException(BaseResponseStatus.UNAUTHORIZED);
            }
            authService.login(loginDto, response);
            return BaseResponse.success();
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(HttpServletRequest httpRequest) {
            String accessToken = cookieUtil.getCookieValue(httpRequest, "access_token");
            authService.logout(accessToken);
            return BaseResponse.success();
    }

    @GetMapping("/profile/petGroomingImage")
    public BaseResponse<List<PetGroomingImageDto>> getPetGroomingImages(@AuthenticationPrincipal Long designerId) {
        List<PetGroomingImageDto> petGroomingImages = designerRetrieveService.getPetGroomingImages(designerId);
        return BaseResponse.success(petGroomingImages);
    }
}
