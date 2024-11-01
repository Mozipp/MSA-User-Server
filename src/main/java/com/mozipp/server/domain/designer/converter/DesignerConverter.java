package com.mozipp.server.domain.designer.converter;

import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.dto.PetShopProfileDto;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;

public class DesignerConverter {
    public static User toUserEntity(DesignerSignUpDto designerSignUpDto, String encodedPassword, Role role) {
        return User.builder()
                .username(designerSignUpDto.getUsername())
                .password(encodedPassword)
                .name(designerSignUpDto.getName())
                .gender(designerSignUpDto.getGender())
                .role(role)
                .build();
    }

    public static DesignerProfileResponse toDeisgnerProfileResponseDto(User user) {
        Designer designer = (Designer) user;
        PetShopProfileDto petShopProfileDto = PetShopProfileDto.builder()
                .petShopName(((Designer) user).getPetShop().getPetShopName())
                .petShopName(((Designer) user).getPetShop().getAddress())
                .petShopName(((Designer) user).getPetShop().getAddressDetail())
                .build();

        return DesignerProfileResponse.builder()
                .name(designer.getName())
                .gender(designer.getGender())
                .isVerified(designer.getIsVerified())
                .petShopProfileDto(petShopProfileDto)
                .petGroomingImageUrl(designer.getPetGroomingImages())
                .build();
    }
}
