package com.mozipp.server.domain.model.service;

import com.mozipp.server.domain.model.converter.ModelConverter;
import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModelRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(ModelSignUpDto request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userRepository.save(ModelConverter.toUserEntity(request, encodedPassword, Role.MODEL));
    }
}
