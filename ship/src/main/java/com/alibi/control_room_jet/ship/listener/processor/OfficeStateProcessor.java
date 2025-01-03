package com.alibi.control_room_jet.ship.listener.processor;

import com.alibi.control_room_jet.common.messages.BoardStateMessage;
import com.alibi.control_room_jet.common.messages.OfficeStateMessage;
import com.alibi.control_room_jet.common.processor.MessageConverter;
import com.alibi.control_room_jet.common.processor.MessageProcessor;
import com.alibi.control_room_jet.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {

    private final MessageConverter messageConverter;
    private final BoardProvider boardProvider;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        boardProvider.getBoards().forEach(board -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new BoardStateMessage(board)));
        });
    }
}
