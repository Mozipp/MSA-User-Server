package com.mozipp.server.domain.model.entity;

import com.mozipp.server.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "user_id")
public class Model extends User {

    private String petName;

    private String petAge;

    private PetGender petGender;

    private String breed;

    private String petImageUrl;
}
