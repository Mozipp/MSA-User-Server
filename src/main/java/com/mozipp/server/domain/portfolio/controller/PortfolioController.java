package com.mozipp.server.domain.portfolio.controller;

import com.mozipp.server.domain.portfolio.dto.PortfolioRequest;
import com.mozipp.server.domain.portfolio.dto.PortfolioResponseDto;
import com.mozipp.server.domain.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<PortfolioResponseDto> createPortfolio(@RequestBody PortfolioRequest request) {
        PortfolioResponseDto response = portfolioService.createPortfolio(request);
        return ResponseEntity.ok(response);
    }
}
