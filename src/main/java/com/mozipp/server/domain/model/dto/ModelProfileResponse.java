package com.mozipp.server.domain.model.dto;

import com.mozipp.server.domain.model.entity.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelProfileResponse {
    private String name;
    private Gender gender;
    private Boolean isVerified;

    @Builder
    public ModelProfileResponse(String name, Gender gender, Boolean isVerified) {
        this.name = name;
        this.gender = gender;
        this.isVerified = isVerified;
    }
}
