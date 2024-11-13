package com.mozipp.server.domain.model.service;

import com.mozipp.server.domain.model.converter.ModelConverter;
import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.model.dto.PetProfileRequest;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.model.repository.ModelRepository;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.repository.UserRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModelRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelRepository modelRepository;

    @Transactional
    public void signUp(ModelSignUpDto request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new BaseException(BaseResponseStatus.CONFLICT_DUPLICATE_ID);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userRepository.save(ModelConverter.toModel(request, encodedPassword, Role.MODEL));
    }

    @Transactional
    public void registerModelPetProfile(PetProfileRequest request, User user) {
        Model model = (Model) user;
        modelRepository.save(ModelConverter.toModelEntity(request, model));
    }
}
