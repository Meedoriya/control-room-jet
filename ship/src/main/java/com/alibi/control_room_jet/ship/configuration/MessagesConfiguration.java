package com.alibi.control_room_jet.ship.configuration;

import com.alibi.control_room_jet.common.processor.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagesConfiguration {

    @Bean
    public MessageConverter converter() {
        return new MessageConverter();
    }
}
