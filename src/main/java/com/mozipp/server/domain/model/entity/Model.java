package com.mozipp.server.domain.model.entity;

import com.mozipp.server.domain.region.entity.PreferRegion;
import com.mozipp.server.domain.user.entity.ViolationStatus;
import com.mozipp.server.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "model_id")
public class Model extends User {

    private String modelImageUrl;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private ViolationStatus violationStatus;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferRegion> preferRegions = new ArrayList<>();
}
