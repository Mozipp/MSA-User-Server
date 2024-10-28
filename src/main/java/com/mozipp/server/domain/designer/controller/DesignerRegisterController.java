package com.mozipp.server.domain.designer.controller;

import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.service.DesignerRegisterService;
import com.mozipp.server.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/designer")
public class DesignerRegisterController {

    private final DesignerRegisterService designerRegisterService;

    // 디자이너 회원가입
    @PostMapping("/sign-up")
    public BaseResponse signUp(@RequestBody DesignerSignUpDto designerSignUpDto){
        return null;
    }


}
