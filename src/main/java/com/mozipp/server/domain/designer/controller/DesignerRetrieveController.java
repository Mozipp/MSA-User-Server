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

//    private final UserMatchService userMatchService;
//    private final AuthService authService;
    private final DesignerRepository designerRepository;

    // 디자이너 프로필 조회
    @GetMapping("/profile/{designerId}")
    public BaseResponse<DesignerProfileResponse> getDesignerProfile(@PathVariable Long designerId) {

        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        DesignerProfileResponse designerProfileResponse = DesignerConverter.toDesignerProfileResponse(designer);
        return BaseResponse.success(designerProfileResponse);
    }

//    @PostMapping("/login")
//    public BaseResponse<AuthResponseDto> login(@Valid @RequestBody DesignerLoginDto loginDto) {
//        try {
//            User user = userMatchService.authenticate(loginDto.getUsername(), loginDto.getPassword());
//            AuthResponseDto authResponse = authService.login(loginDto);
//            return BaseResponse.success();
//        } catch (Exception e) {
//            return BaseResponse.fail(UNAUTHORIZED);
//        }
//    }
}
