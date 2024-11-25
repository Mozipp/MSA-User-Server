package com.mozipp.server.domain.designer.service;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerLicenseImageDto;
import com.mozipp.server.domain.designer.dto.DesignerPetGroomingImageDto;
import com.mozipp.server.domain.designer.dto.DesignerProfileRequest;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import com.mozipp.server.domain.petgroomingimage.repository.PetGroomingImageRepository;
import com.mozipp.server.domain.petshop.entity.PetShop;
import com.mozipp.server.domain.petshop.repository.PetShopRepository;
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
public class DesignerRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PetShopRepository petShopRepository;
    private final S3FileService s3FileService;
    private final DesignerRepository designerRepository;
    private final PetGroomingImageRepository petGroomingImageRepository;

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
    public void registerDesignerProfile(DesignerProfileRequest request, Long designerId) {

        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        PetShop petShop = DesignerConverter.toPetShop(request);

        designer.updatePetShop(petShop);
        designer.updateCareer(request.getCareer());
        petShopRepository.save(petShop);
    }

    @Transactional
    public void registerLicenseImage(DesignerLicenseImageDto request, Long designerId) {

        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        if(request.getLicenseImage() != null && !request.getLicenseImage().isEmpty()) {
            MultipartFile licenseImage = request.getLicenseImage();
            String imageUrl = s3FileService.uploadFile(licenseImage);
            designer.updateLicenseImageUrl(imageUrl);
        }
    }

    @Transactional
    public void registerPetGroomingImage(DesignerPetGroomingImageDto request, Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        if(request.getPetGroomingImage() != null && !request.getPetGroomingImage().isEmpty()) {
            MultipartFile licenseImage = request.getPetGroomingImage();
            String imageUrl = s3FileService.uploadFile(licenseImage);
            PetGroomingImage petGroomingImage = PetGroomingImage.builder()
                    .imageUrl(imageUrl)
                    .build();
            petGroomingImage.updateDesigner(designer);
            petGroomingImageRepository.save(petGroomingImage);
        }
    }
}
