package com.mozipp.server.domain.designer.entity;

import com.mozipp.server.domain.petshop.entity.PetShop;
import com.mozipp.server.domain.user.ViolationStatus;
import com.mozipp.server.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "designer_id")
public class Designer extends User {

    private String licenseImageUrl;

    private Boolean isVerified;

    private String career;

    @Enumerated(EnumType.STRING)
    private ViolationStatus violationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_shop_id")
    private PetShop petShop;
}
