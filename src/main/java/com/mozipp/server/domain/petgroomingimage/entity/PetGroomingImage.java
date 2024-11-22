package com.mozipp.server.domain.petgroomingimage.entity;

import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.user.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetGroomingImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;

    @Builder
    public PetGroomingImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateDesigner(Designer designer) {
        this.designer = designer;
        designer.addPetGroomingImage(this);
    }
}
