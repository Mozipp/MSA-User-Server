package com.mozipp.server.domain.model.dto;

import com.mozipp.server.domain.user.entity.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelSignUpDto {
    private String name;
    private String age;
    private Gender gender;
    private String username;
    private String password;
}
