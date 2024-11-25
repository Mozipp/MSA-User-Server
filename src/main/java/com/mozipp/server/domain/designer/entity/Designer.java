package com.mozipp.server.domain.designer.entity;

import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import com.mozipp.server.domain.petshop.entity.PetShop;
import com.mozipp.server.domain.user.entity.User;
import com.mozipp.server.product.DesignerProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "designer_id")
public class Designer extends User {

    private String licenseImageUrl;

    private String career;

    private Boolean isVerified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_shop_id")
    private PetShop petShop;

    @OneToMany(mappedBy = "designer")
    private List<PetGroomingImage> petGroomingImages = new ArrayList<>();

    @OneToMany(mappedBy = "designer")
    private List<DesignerProduct> products = new ArrayList<>();

    public void updatePetShop(PetShop petShop) {
        this.petShop = petShop;
        petShop.addDesigner(this);
    }

    public void updateCareer(String career) {
        this.career = career;
    }

    public void updateLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
    }

    public void addPetGroomingImage(PetGroomingImage petGroomingImage) {
        petGroomingImages.add(petGroomingImage);
    }
}
