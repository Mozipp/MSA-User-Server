package com.mozipp.server.domain.designer.entity;

import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import com.mozipp.server.domain.petshop.entity.PetShop;
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
@PrimaryKeyJoinColumn(name = "designer_id")
public class Designer extends User {

    private String licenseImageUrl;

    private Boolean isVerified;

    private String career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_shop_id")
    private PetShop petShop;

    @OneToMany(mappedBy = "designer")
    private List<PetGroomingImage> petGroomingImages = new ArrayList<>();

    public void updatePetShop(PetShop petShop) {
        this.petShop = petShop;
    }

    public void updateCareer(String career) {
        this.career = career;
    }
}
