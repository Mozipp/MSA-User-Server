package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.designer.service.DesignerRetrieveService;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.service.UserFindService;
import com.mozipp.server.domain.user.service.UserMatchService;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponse;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.mozipp.server.global.handler.response.BaseResponseStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/designer")
public class DesignerRetrieveController {

    private final UserMatchService userMatchService;
    private final AuthService authService;
    private final UserFindService userFindService;
    private final DesignerRetrieveService designerRetrieveService;

    // 디자이너 프로필 조회
    @GetMapping("/profile")
    public BaseResponse<DesignerProfileResponse> getDesignerProfile(@RequestHeader("Authorization") String authorizationHeader) {
        Long designerId = userFindService.getUserId(authorizationHeader);
        DesignerProfileResponse response = designerRetrieveService.getDesignerProfile(designerId);
        return BaseResponse.success(response);
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
