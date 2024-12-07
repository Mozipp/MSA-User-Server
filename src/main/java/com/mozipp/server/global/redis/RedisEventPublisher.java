package com.mozipp.server.global.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozipp.server.domain.portfolio.dto.PortfolioResultEvent;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisEventPublisher {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisEventPublisher(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishPortfolioCreationSuccess(PortfolioResultEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            redisTemplate.convertAndSend("PORTFOLIO_CREATION_SUCCESS", message);
        } catch (JsonProcessingException e) {
            throw new BaseException(BaseResponseStatus.JSON_PROCESSING_ERROR);
        }
    }

    public void publishPortfolioCreationFail(PortfolioResultEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            redisTemplate.convertAndSend("PORTFOLIO_CREATION_FAIL", message);
        } catch (JsonProcessingException e) {
            throw new BaseException(BaseResponseStatus.JSON_PROCESSING_ERROR);
        }
    }
}
