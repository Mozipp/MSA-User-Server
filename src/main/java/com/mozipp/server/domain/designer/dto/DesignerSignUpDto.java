package com.mozipp.server.domain.designer.dto;

import com.mozipp.server.domain.model.entity.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerSignUpDto {
    private String name;
    private Gender gender;
    private String username;
    private String password;
}
