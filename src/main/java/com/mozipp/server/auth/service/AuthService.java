package com.mozipp.server.auth.service;

import com.mozipp.server.auth.dto.AuthResponseDto;
import com.mozipp.server.domain.designer.dto.DesignerLoginDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final WebClient webClient;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public void login(DesignerLoginDto loginDto, HttpServletResponse response) {
        logger.info("Sending login request to Auth server for username: {}", loginDto.getUsername());
        // WebClient를 사용하여 인증 서버에 로그인 요청 보내기
        Mono<Void> authResponseMono = webClient.post()
                .uri(authServiceUrl + "/auth/login")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .bodyValue(loginDto)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        // 인증 서버의 Set-Cookie 헤더 추출
                        HttpHeaders headers = clientResponse.headers().asHttpHeaders();
                        List<String> setCookieHeaders = headers.get(HttpHeaders.SET_COOKIE);
                        if (setCookieHeaders != null) {
                            for (String setCookie : setCookieHeaders) {
                                // 클라이언트 응답에 Set-Cookie 헤더 추가
                                response.addHeader(HttpHeaders.SET_COOKIE, setCookie);
                            }
                        }
                        // 빈 Mono 반환
                        return Mono.empty();
                    } else {
                        // 오류 처리
                        return clientResponse.createException()
                                .flatMap(error -> Mono.error(new RuntimeException("Auth server login failed")));
                    }
                });

        // 블로킹 호출로 인증 서버 응답 처리
        authResponseMono.block();
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
