package com.mozipp.server.domain.user.service;

import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.repository.UserRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMatchService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.UNAUTHORIZED_ID));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BaseException(BaseResponseStatus.UNAUTHORIZED_PASSWORD);
        }

        return user;
    }
}
