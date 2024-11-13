package com.mozipp.server.domain.model.converter;

import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.model.dto.PetProfileRequest;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;

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
                .petName(model.getName())
                .petAge(model.getPetAge())
                .petGender(model.getPetGender())
                .breed(model.getBreed())
                .petImageUrl(model.getPetImageUrl())
                .build();
    }

    public static ModelProfileResponse toModelProfileResponse(User user) {
        return ModelProfileResponse.builder()
                .name(user.getName())
                .gender(user.getGender())
                .isVerified(user.getIsVerified())
                .build();
    }

    public static Model toModelEntity(PetProfileRequest request, User user) {
        return Model.builder()
                .petName(request.getPetName())
                .petAge(request.getPetAge())
                .petGender(request.getPetGender())
                .breed(request.getBreed())
                .build();
    }
}
