package com.mozipp.server.domain.designer.dto;

import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import com.mozipp.server.domain.model.entity.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignerProfileResponse {
    private String name;
    private Gender gender;
    private Boolean isVerified;
    private PetShopProfileDto petShopProfileDto;
    private List<PetGroomingImage> petGroomingImageUrl;

    @Builder
    public DesignerProfileResponse(String name, Gender gender, Boolean isVerified, PetShopProfileDto petShopProfileDto, List<PetGroomingImage> petGroomingImageUrl) {
        this.name = name;
        this.gender = gender;
        this.isVerified = isVerified;
        this.petShopProfileDto = petShopProfileDto;
        this.petGroomingImageUrl = petGroomingImageUrl;
    }
}
