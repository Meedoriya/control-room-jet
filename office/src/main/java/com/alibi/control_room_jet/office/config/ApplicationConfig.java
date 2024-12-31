package com.alibi.control_room_jet.office.config;

import static java.util.concurrent.TimeUnit.MINUTES;

import com.alibi.control_room_jet.common.processor.MessageConverter;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

@Configuration
public class ApplicationConfig {

    @Bean
    public MessageConverter converter() {
        return new MessageConverter();
    }

    @Bean
    public Cache<String, WebSocketSession> sessionCache() {
        return Caffeine.newBuilder()
            .expireAfterWrite(20, MINUTES)
            .build();
    }
}
