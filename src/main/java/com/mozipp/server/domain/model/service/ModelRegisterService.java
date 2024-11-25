package com.mozipp.server.domain.model.service;

import com.mozipp.server.domain.model.converter.ModelConverter;
import com.mozipp.server.domain.model.dto.ModelPetImageDto;
import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.model.dto.PetProfileRequest;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.model.repository.ModelRepository;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.repository.UserRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import com.mozipp.server.global.s3.S3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ModelRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelRepository modelRepository;
    private final S3FileService s3FileService;

    @Transactional
    public void signUp(ModelSignUpDto request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new BaseException(BaseResponseStatus.CONFLICT_DUPLICATE_ID);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userRepository.save(ModelConverter.toModel(request, encodedPassword, Role.MODEL));
    }

    @Transactional
    public void registerModelPetProfile(PetProfileRequest request, Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        model.updatePetProfile(
                request.getPetName(),
                request.getPetAge(),
                request.getPetGender(),
                request.getBreed()
        );
    }

    @Transactional
    public void registerPetImage(ModelPetImageDto request, Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));

        if(request.getPetImage() != null && !request.getPetImage().isEmpty()) {
            MultipartFile licenseImage = request.getPetImage();
            String imageUrl = s3FileService.uploadFile(licenseImage);
            model.updatePetImage(imageUrl);
        }
    }
}
