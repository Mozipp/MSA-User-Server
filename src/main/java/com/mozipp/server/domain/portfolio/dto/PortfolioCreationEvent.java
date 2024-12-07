package com.mozipp.server.domain.portfolio.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PortfolioCreationEvent {
    private final Long designerId;
    private final String naverPlaceUrl;
    private final Long productId;
}
