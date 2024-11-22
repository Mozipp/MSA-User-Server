package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponse;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
