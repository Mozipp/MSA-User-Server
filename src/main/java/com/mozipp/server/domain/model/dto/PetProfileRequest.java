package com.mozipp.server.domain.model.dto;

import com.mozipp.server.domain.model.entity.PetGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetProfileRequest {
    private String petName;
    private String petAge;
    private PetGender petGender;
    private String breed;
}
