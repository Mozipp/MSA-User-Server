package com.mozipp.server.domain.user.service;

import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.repository.UserRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import com.mozipp.server.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public Long getUserId(String accessToken) {
        Claims claims = jwtUtil.getClaimsFromToken(accessToken);
        String username = claims.getSubject();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
        return user.getId();
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));
        return user.getId();
    }
}
