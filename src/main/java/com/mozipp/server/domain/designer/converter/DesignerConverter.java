package com.mozipp.server.domain.designer.converter;

import com.mozipp.server.domain.designer.dto.DesignerSignUpDto;
import com.mozipp.server.domain.user.entity.Role;
import com.mozipp.server.domain.user.entity.User;

public class DesignerConverter {
    public static User toUserEntity(DesignerSignUpDto designerSignUpDto, String encodedPassword, Role role) {
        return User.builder()
                .username(designerSignUpDto.getUsername())
                .password(encodedPassword)
                .name(designerSignUpDto.getName())
                .gender(designerSignUpDto.getGender())
                .age(designerSignUpDto.getAge())
                .role(role)
                .build();
    }
}
