package com.mozipp.server.domain.petshop.entity;

import com.mozipp.server.domain.BaseTimeEntity;
import com.mozipp.server.domain.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetShop extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_shop_id")
    private Long id;

    private String petShopName;

    private String address;

    private String addressDetail;

    @OneToMany(mappedBy = "petShop")
    private List<Designer> designers = new ArrayList<>();
}
