package com.mozipp.server.domain.user.service;

import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMatchService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(String username, String password) {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new RuntimeException("인증 실패");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("인증 실패");
        }

        return user;
    }
}
