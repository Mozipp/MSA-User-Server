package com.mozipp.server.auth.controller;

import com.mozipp.server.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Jaebin")
public class TestController {

    private final AuthService authService;
        @PostMapping("/test")
    public ResponseEntity<String> test(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 제거
            return ResponseEntity.ok(authService.test(accessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
