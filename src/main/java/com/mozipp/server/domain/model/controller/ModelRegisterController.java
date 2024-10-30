package com.mozipp.server.domain.model.controller;

import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.model.service.ModelRegisterService;
import com.mozipp.server.global.handler.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model")
public class ModelRegisterController {

    private final ModelRegisterService modelRegisterService;

    @PostMapping("/sign-up")
    public BaseResponse<Object> signUp(@RequestBody ModelSignUpDto modelSignUpDto){
        modelRegisterService.signUp(modelSignUpDto);
        return BaseResponse.success();
    }

}
