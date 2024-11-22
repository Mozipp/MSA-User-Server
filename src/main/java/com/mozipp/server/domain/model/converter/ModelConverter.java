package com.mozipp.server.domain.model.converter;

import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.user.entity.Role;

public class ModelConverter {
    public static Model toModel(ModelSignUpDto modelSignUpDto, String encodedPassword, Role role) {
        return Model.builder()
                .username(modelSignUpDto.getUsername())
                .password(encodedPassword)
                .name(modelSignUpDto.getName())
                .gender(modelSignUpDto.getGender())
                .role(role)
                .build();
    }

    public static PetProfileResponse toPetProfileResponse(Model model) {
        return PetProfileResponse.builder()
                .petName(model.getPetName())
                .petAge(model.getPetAge())
                .petGender(model.getPetGender())
                .breed(model.getBreed())
                .petImageUrl(model.getPetImageUrl())
                .build();
    }

    public static ModelProfileResponse toModelProfileResponse(Model model) {
        return ModelProfileResponse.builder()
                .name(model.getName())
                .gender(model.getGender())
                .build();
    }
}
