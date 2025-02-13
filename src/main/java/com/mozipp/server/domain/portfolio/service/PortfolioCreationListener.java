package com.mozipp.server.domain.portfolio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozipp.server.domain.portfolio.dto.PortfolioCreationEvent;
import com.mozipp.server.domain.portfolio.dto.PortfolioRequest;
import com.mozipp.server.domain.portfolio.dto.PortfolioResponseDto;
import com.mozipp.server.domain.portfolio.dto.PortfolioResultEvent;
import com.mozipp.server.global.redis.RedisEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PortfolioCreationListener {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioCreationListener.class);
    private final PortfolioService portfolioService;
    private final RedisEventPublisher redisEventPublisher;
    private final ObjectMapper objectMapper;

    public PortfolioCreationListener(PortfolioService portfolioService, RedisEventPublisher redisEventPublisher, ObjectMapper objectMapper) {
        this.portfolioService = portfolioService;
        this.redisEventPublisher = redisEventPublisher;
        this.objectMapper = objectMapper;
    }

    public void handlePortfolioCreationRequest(String message) {
        logger.info("Received message: {}", message);
        try {
            PortfolioCreationEvent event = objectMapper.readValue(message, PortfolioCreationEvent.class);
            logger.info("Parsed event: {}", event);

            try {
                PortfolioRequest portfolioRequest = new PortfolioRequest(event.getDesignerId(), event.getNaverPlaceUrl());
                PortfolioResponseDto response = portfolioService.createOrUpdatePortfolio(portfolioRequest);

                // 성공 이벤트 발행
                PortfolioResultEvent successEvent = new PortfolioResultEvent(event.getProductId(), response.getPortfolioId());
                redisEventPublisher.publishPortfolioCreationSuccess(successEvent);

                logger.info("Portfolio created/updated successfully for productId={}, portfolioId={}", event.getProductId(), response.getPortfolioId());
            } catch (Exception e) {
                // 실패 이벤트 발행
                PortfolioResultEvent failEvent = new PortfolioResultEvent(event.getProductId(), null);
                redisEventPublisher.publishPortfolioCreationFail(failEvent);

                logger.error("Failed to create Portfolio for productId={}", event.getProductId(), e);
            }

        } catch (Exception e) {
            logger.error("Failed to parse PortfolioCreationEvent", e);
        }
    }
}