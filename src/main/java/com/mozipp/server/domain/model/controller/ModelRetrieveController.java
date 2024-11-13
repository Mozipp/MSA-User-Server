package com.mozipp.server.domain.model.controller;

import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
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
@RequestMapping("/api/model")
public class ModelRetrieveController {

    private final ModelRetrieveService modelRetrieveService;
    private final UserFindService userFindService;

    // Model 프로필 조회
    @GetMapping("/profile")
    public BaseResponse<ModelProfileResponse> getModelProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        ModelProfileResponse response = modelRetrieveService.getModelProfile(user);
        return BaseResponse.success(response);
    }

    // Model 애완동물 프로필 조회
    @GetMapping("/pet/profile")
    public BaseResponse<PetProfileResponse> getPetProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        PetProfileResponse response = modelRetrieveService.getPetProfile(user);
        return BaseResponse.success(response);
    }
}
