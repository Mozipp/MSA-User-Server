package com.mozipp.server.auth.service;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import com.mozipp.server.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public AuthResponseDto login(DesignerLoginDto loginDto) {
        logger.info("Sending login request to Auth server for username: {}", loginDto.getUsername());
        return webClient.post()
                .uri(authServiceUrl + "/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginDto)
                .retrieve()
                .bodyToMono(AuthResponseDto.class)
                .doOnNext(response -> logger.info("Received login response from Auth server"))
                .doOnError(error -> logger.error("Login request to Auth server failed: {}", error.getMessage()))
                .block(); // 블로킹 호출
    }

    public void logout(String accessToken) {
        logger.info("Sending logout request to Auth server with access token");
        webClient.post()
                .uri(authServiceUrl + "/auth/logout")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(response -> logger.info("Logout successful on Auth server"))
                .doOnError(error -> logger.error("Logout request to Auth server failed: {}", error.getMessage()))
                .block(); // 블로킹 호출
    }
}
