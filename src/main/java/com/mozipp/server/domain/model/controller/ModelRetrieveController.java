package com.mozipp.server.domain.model.controller;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.service.ModelRetrieveService;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.domain.user.service.UserMatchService;
import com.mozipp.server.global.handler.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.mozipp.server.global.handler.response.BaseResponseStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/model")
public class ModelRetrieveController {

    private final ModelRetrieveService modelRetrieveService;
    private final UserFindService userFindService;
    private final UserMatchService userMatchService;
    private final AuthService authService;

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

    @PostMapping("/login")
    public BaseResponse<AuthResponseDto> login(@Valid @RequestBody DesignerLoginDto loginDto) {
        try {
            User user = userMatchService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            AuthResponseDto authResponse = authService.login(loginDto);
            return BaseResponse.success(authResponse);
        } catch (Exception e) {
            return BaseResponse.fail(UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 제거
            authService.logout(accessToken);
            return BaseResponse.success();
        } catch (Exception e) {
            return BaseResponse.fail(UNAUTHORIZED);
        }
    }
}
