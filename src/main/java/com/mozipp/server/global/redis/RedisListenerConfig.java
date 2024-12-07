package com.mozipp.server.global.redis;

import com.mozipp.server.domain.portfolio.service.PortfolioCreationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {

    private final PortfolioCreationListener portfolioCreationListener;

    public RedisListenerConfig(PortfolioCreationListener portfolioCreationListener) {
        this.portfolioCreationListener = portfolioCreationListener;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(LettuceConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // PORTFOLIO_CREATION_REQUEST 채널 구독
        container.addMessageListener(
                new MessageListenerAdapter(portfolioCreationListener, "handlePortfolioCreationRequest"),
                new PatternTopic("PORTFOLIO_CREATION_REQUEST")
        );

        return container;
    }
}
