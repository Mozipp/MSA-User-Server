package com.mozipp.server.global.redis;

import com.mozipp.server.domain.portfolio.service.PortfolioCreationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisListenerConfig.class);

    private final PortfolioCreationListener portfolioCreationListener;

    public RedisListenerConfig(PortfolioCreationListener portfolioCreationListener) {
        if (portfolioCreationListener == null) {
            throw new IllegalStateException("PortfolioCreationListener is not properly injected!");
        }
        this.portfolioCreationListener = portfolioCreationListener;
    }

    /**
     * MessageListenerAdapter Bean 등록
     * - Listener 메서드와 메서드 이름을 명시적으로 매핑
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(portfolioCreationListener, "handlePortfolioCreationRequest");
        return adapter;
    }

    /**
     * RedisMessageListenerContainer Bean 등록
     * - MessageListenerAdapter와 Redis Connection을 연결
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            LettuceConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // MessageListenerAdapter와 채널 연결
        container.addMessageListener(messageListenerAdapter, new PatternTopic("PORTFOLIO_CREATION_REQUEST"));

        logger.info("RedisMessageListenerContainer initialized with adapter: {}", messageListenerAdapter);
        return container;
    }
}
