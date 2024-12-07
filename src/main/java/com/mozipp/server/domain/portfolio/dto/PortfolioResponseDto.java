package com.mozipp.server.domain.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PortfolioResponseDto {
    private Long portfolioId;
    private String naverPlaceUrl;
    private Long designerId;
}
