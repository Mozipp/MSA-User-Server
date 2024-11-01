package com.mozipp.server.domain.designer.service;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerProfileRequest;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.petshop.entity.PetShop;
import com.mozipp.server.domain.petshop.repository.PetShopRepository;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DesignerRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PetShopRepository petShopRepository;
    private final DesignerRepository designerRepository;

    @Transactional
    public void signUp(DesignerSignUpDto request) {

        // 중복된 아이디 확인
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userRepository.save(DesignerConverter.toUserEntity(request, encodedPassword, Role.DESIGNER));
    }

    @Transactional
    public void registerProfile(User user, DesignerProfileRequest request) {

        Designer designer = (Designer) user;

        PetShop petShop = PetShop.builder()
                .petShopName(request.getPetShopName())
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .build();

        petShopRepository.save(petShop);

        designer.updatePetShop(petShop);
        designer.updateCareer(request.getCareer());

        designerRepository.save(designer);
    }
}
