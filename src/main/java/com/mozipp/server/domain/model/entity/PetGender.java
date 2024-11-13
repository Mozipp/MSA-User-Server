package com.mozipp.server.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetGender {
    MALE("수컷"), FEMALE("암컷");

    private final String value;
}
