package com.mozipp.server.domain.designer.service;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerLicenseImageDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileRequest;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.petshop.entity.PetShop;
import com.mozipp.server.domain.petshop.repository.PetShopRepository;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;
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
public class DesignerRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PetShopRepository petShopRepository;
    private final S3FileService s3FileService;

    @Transactional
    public void signUp(DesignerSignUpDto request) {

        // 중복된 아이디 확인
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BaseException(BaseResponseStatus.CONFLICT_DUPLICATE_ID);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Designer designer = DesignerConverter.toDesigner(request, encodedPassword, Role.DESIGNER);
        userRepository.save(designer);
    }

    @Transactional
    public void registerDesignerProfile(DesignerProfileRequest request, User user) {

        Designer designer = (Designer) user;

        PetShop petShop = DesignerConverter.toPetShop(request);

        designer.updatePetShop(petShop);
        designer.updateCareer(request.getCareer());
        petShopRepository.save(petShop);
    }

    @Transactional
    public void registerLicenseImage(DesignerLicenseImageDto request, User user) {
        Designer designer = (Designer) user;

        if(request.getLicenseImage() != null && !request.getLicenseImage().isEmpty()) {
            MultipartFile licenseImage = request.getLicenseImage();
            String imageUrl = s3FileService.uploadFile(licenseImage);
            designer.updateLicenseImageUrl(imageUrl);
        }
    }
}
