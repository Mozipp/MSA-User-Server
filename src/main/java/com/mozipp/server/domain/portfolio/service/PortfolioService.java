package com.mozipp.server.domain.portfolio.service;

import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.portfolio.dto.PortfolioRequest;
import com.mozipp.server.domain.portfolio.dto.PortfolioResponseDto;
import com.mozipp.server.domain.portfolio.entity.Portfolio;
import com.mozipp.server.domain.portfolio.repository.PortfolioRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final DesignerRepository designerRepository;

    @Transactional
    public PortfolioResponseDto createOrUpdatePortfolio(PortfolioRequest request) {
        Designer designer = designerRepository.findById(request.getDesignerId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));

        Optional<Portfolio> existingPortfolioOpt = portfolioRepository.findByDesigner(designer);
        Portfolio portfolio;

        if (existingPortfolioOpt.isPresent()) {
            // 기존 Portfolio가 있을 경우, 업데이트
            portfolio = existingPortfolioOpt.get();
            portfolio.setNaverPlaceUrl(request.getNaverPlaceUrl());
        } else {
            // 새로운 Portfolio 생성
            portfolio = new Portfolio(request.getNaverPlaceUrl(), designer);
        }

        portfolioRepository.save(portfolio);

        return new PortfolioResponseDto(portfolio.getId(), portfolio.getNaverPlaceUrl(), portfolio.getDesigner().getId());
    }
}
