package com.mozipp.server.domain.model.dto;

import com.mozipp.server.domain.model.entity.PetGender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetProfileResponse {
    private String petName;
    private String petAge;
    private PetGender petGender;
    private String breed;
    private String petImageUrl;

    @Builder
    public PetProfileResponse(String petName, String petAge, PetGender petGender, String breed, String petImageUrl) {
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.breed = breed;
        this.petImageUrl = petImageUrl;
    }
}
