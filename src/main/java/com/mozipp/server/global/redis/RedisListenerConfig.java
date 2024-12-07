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

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(LettuceConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        MessageListenerAdapter adapter = new MessageListenerAdapter(portfolioCreationListener);
        adapter.setDefaultListenerMethod("handlePortfolioCreationRequest"); // 명시적으로 메서드 매핑 설정

        container.addMessageListener(adapter, new PatternTopic("PORTFOLIO_CREATION_REQUEST"));

        logger.info("RedisMessageListenerContainer initialized: {}", container);
        return container;
    }
}
