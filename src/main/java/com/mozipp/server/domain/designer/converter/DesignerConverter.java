package com.mozipp.server.domain.designer.converter;

import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.dto.PetShopProfileDto;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;

public class DesignerConverter {
    public static Designer toDesigner(DesignerSignUpDto designerSignUpDto, String encodedPassword, Role role) {
        return Designer.builder()
                .username(designerSignUpDto.getUsername())
                .password(encodedPassword)
                .name(designerSignUpDto.getName())
                .gender(designerSignUpDto.getGender())
                .role(role)
                .build();
    }

    public static DesignerProfileResponse toDeisgnerProfileResponse(User user) {
        Designer designer = (Designer) user;
        PetShopProfileDto petShopProfileDto = PetShopProfileDto.builder()
                .petShopName(designer.getPetShop().getPetShopName())
                .petShopName(designer.getPetShop().getAddress())
                .petShopName(designer.getPetShop().getAddressDetail())
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
