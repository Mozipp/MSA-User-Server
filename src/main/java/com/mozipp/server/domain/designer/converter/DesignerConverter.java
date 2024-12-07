package com.mozipp.server.domain.designer.converter;

import com.mozipp.server.domain.designer.dto.*;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import com.mozipp.server.domain.petshop.entity.PetShop;
import com.mozipp.server.domain.user.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

public class DesignerConverter {
    public static Designer toDesigner(DesignerSignUpDto designerSignUpDto, String encodedPassword, Role role) {
        return Designer.builder()
                .username(designerSignUpDto.getUsername())
                .password(encodedPassword)
                .name(designerSignUpDto.getName())
                .gender(designerSignUpDto.getGender())
                .isVerified(false)
                .role(role)
                .build();
    }

    public static DesignerProfileResponse toDesignerProfileResponse(Designer designer) {
        PetShopProfileDto petShopProfileDto = null;
        if(designer.getPetShop() != null) {
            petShopProfileDto = PetShopProfileDto.builder()
                    .petShopName(designer.getPetShop().getPetShopName())
                    .address(designer.getPetShop().getAddress())
                    .addressDetail(designer.getPetShop().getAddressDetail())
                    .build();
        }

        List<PetGroomingImage> petGroomingImages = designer.getPetGroomingImages();
        List<PetGroomingImageDto> petGroomingImageDtos = petGroomingImages.stream()
                .map(image -> PetGroomingImageDto.builder()
                        .imageUrl(image.getImageUrl())
                        .build())
                .collect(Collectors.toList());

        return DesignerProfileResponse.builder()
                .name(designer.getName())
                .gender(designer.getGender())
                .career(designer.getCareer())
                .isVerified(designer.getIsVerified())
                .petShop(petShopProfileDto)
                .petGroomingImageUrl(petGroomingImageDtos)
                .build();
    }

    public static PetShop toPetShop(DesignerProfileRequest request){
        return PetShop.builder()
                .petShopName(request.getPetShopName())
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .build();
    }

    public static List<PetGroomingImageDto> toPetGroomingImageDtos(List<PetGroomingImage> images) {
        List<PetGroomingImageDto> petGroomingImageDtos = toPetGroomingImagesDtos(images);
        return petGroomingImageDtos;
    }


    private static List<PetGroomingImageDto> toPetGroomingImagesDtos(List<PetGroomingImage> petGroomingImages) {
        return petGroomingImages.stream()
                .map(image -> PetGroomingImageDto.builder()
                        .imageUrl(image.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }
}

