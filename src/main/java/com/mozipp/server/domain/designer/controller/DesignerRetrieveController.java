package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.auth.service.AuthService;
import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.user.entity.User;
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

    private final AuthService authService;
    private final DesignerRepository designerRepository;
    private final UserMatchService userMatchService;

    // 디자이너 프로필 조회
    @GetMapping("/profile/{designerId}")
    public BaseResponse<DesignerProfileResponse> getDesignerProfile(@PathVariable Long designerId) {

        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        DesignerProfileResponse designerProfileResponse = DesignerConverter.toDesignerProfileResponse(designer);
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
