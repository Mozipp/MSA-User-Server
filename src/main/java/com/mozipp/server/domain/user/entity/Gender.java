package com.mozipp.server.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남성"), FEMALE("여성");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
