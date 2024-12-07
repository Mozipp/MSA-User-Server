package com.mozipp.server.domain.portfolio.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PortfolioResultEvent {
    private final Long productId;
    private final Long portfolioId;
}
