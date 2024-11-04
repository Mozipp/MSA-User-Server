package com.mozipp.server.domain.model.entity;

import com.mozipp.server.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "model_id")
public class Model extends User {

    private String petName;

    private String petAge;

    private String breed;

    private String petImageUrl;
}
