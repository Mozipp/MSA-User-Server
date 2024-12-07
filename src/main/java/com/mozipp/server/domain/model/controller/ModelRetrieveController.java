package com.mozipp.server.domain.model.controller;

import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.service.ModelRetrieveService;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserFindService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/model")
public class ModelRetrieveController {

    private final ModelRetrieveService modelRetrieveService;
    private final UserFindService userFindService;
    private final UserMatchService userMatchService;
    private final AuthService authService;
    private final CookieUtil cookieUtil;

    // Model 프로필 조회
    @GetMapping("/profile")
    public BaseResponse<ModelProfileResponse> getModelProfile(@AuthenticationPrincipal Long modelId) {
        ModelProfileResponse response = modelRetrieveService.getModelProfile(modelId);
        return BaseResponse.success(response);
    }

    // Model 애완동물 프로필 조회
    @GetMapping("/pet/profile")
    public BaseResponse<PetProfileResponse> getPetProfile(@AuthenticationPrincipal Long modelId) {
        PetProfileResponse response = modelRetrieveService.getPetProfile(modelId);
        return BaseResponse.success(response);
    }

    @PostMapping("/login")
    public BaseResponse<Void> login(@Valid @RequestBody DesignerLoginDto loginDto, HttpServletResponse response) {
            User user = userMatchService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            if(user.getRole() == Role.DESIGNER){
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
    }
