package com.mozipp.server.domain.designer.dto;

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
    private String career;
    private Boolean isVerified;
    private PetShopProfileDto petShop;
    private List<PetGroomingImageDto> petGroomingImageUrl;

    @Builder
    public DesignerProfileResponse(String name, Gender gender, String career, Boolean isVerified, PetShopProfileDto petShop, List<PetGroomingImageDto> petGroomingImageUrl) {
        this.name = name;
        this.gender = gender;
        this.career = career;
        this.isVerified = isVerified;
        this.petShop = petShop;
        this.petGroomingImageUrl = petGroomingImageUrl;
    }
}
