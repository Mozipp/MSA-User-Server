package com.mozipp.server.domain.designer.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetShopProfileDto {
    private String petShopName;
    private String address;
    private String addressDetail;

    @Builder
    public PetShopProfileDto(String petShopName, String address, String addressDetail) {
        this.petShopName = petShopName;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
