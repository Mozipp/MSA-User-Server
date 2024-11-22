package com.mozipp.server.domain.model.controller;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.model.repository.ModelRepository;
import com.mozipp.server.domain.model.service.ModelRetrieveService;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/model")
public class ModelRetrieveController {

    private final ModelRetrieveService modelRetrieveService;
    private final UserFindService userFindService;

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
