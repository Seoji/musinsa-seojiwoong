package org.musinsa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import org.musinsa.component.RedisSubscriber;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisSubscriber redisSubscriber;

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(this.redisSubscriber);
    }

    @Bean
    ChannelTopic categoryTopic() {
       return new ChannelTopic("category");
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(messageListener(), categoryTopic());
        return container;
    }

}
