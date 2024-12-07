package com.mozipp.server.domain.portfolio.entity;

import com.mozipp.server.domain.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naverPlaceUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Designer designer;

    public Portfolio(String naverPlaceUrl, Designer designer) {
        this.naverPlaceUrl = naverPlaceUrl;
        this.designer = designer;
    }
}
