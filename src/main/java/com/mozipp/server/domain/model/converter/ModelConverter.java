package com.mozipp.server.domain.model.converter;

import com.mozipp.server.domain.model.dto.ModelSignUpDto;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;

public class ModelConverter {
    public static User toUserEntity(ModelSignUpDto modelSignUpDto, String encodedPassword, Role role) {
        return User.builder()
                .username(modelSignUpDto.getUsername())
                .password(encodedPassword)
                .name(modelSignUpDto.getName())
                .gender(modelSignUpDto.getGender())
                .role(role)
                .build();
    }
}
