package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.service.DesignerRetrieveService;
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
@RequestMapping("/api/designer")
public class DesignerRetrieveController {

    private final DesignerRetrieveService designerRetrieveService;
    private final UserFindService userFindService;

    @GetMapping("/profile")
    public BaseResponse<DesignerProfileResponse> getDesignerProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        DesignerProfileResponse designerProfileResponse = DesignerConverter.toDeisgnerProfileResponseDto(user);
        return BaseResponse.success(designerProfileResponse);
    }
}
