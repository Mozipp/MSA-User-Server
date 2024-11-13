package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.service.DesignerRetrieveService;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.domain.user.service.UserMatchService;
import com.mozipp.server.global.handler.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.mozipp.server.global.handler.response.BaseResponseStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/designer")
public class DesignerRetrieveController {

    private final DesignerRetrieveService designerRetrieveService;
    private final UserFindService userFindService;
    private final UserMatchService userMatchService;
    private final AuthService authService;

    // 디자이너 프로필 조회
    @GetMapping("/profile")
    public BaseResponse<DesignerProfileResponse> getDesignerProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userFindService.findByUserDetails(userDetails);
        DesignerProfileResponse designerProfileResponse = DesignerConverter.toDesignerProfileResponse(user);
        return BaseResponse.success(designerProfileResponse);
    }

    @PostMapping("/login")
    public BaseResponse<AuthResponseDto> login(@Valid @RequestBody DesignerLoginDto loginDto) {
        try {
            User user = userMatchService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            AuthResponseDto authResponse = authService.login(loginDto);
            return BaseResponse.success();
        } catch (Exception e) {
            return BaseResponse.fail(UNAUTHORIZED);
        }
    }
}
