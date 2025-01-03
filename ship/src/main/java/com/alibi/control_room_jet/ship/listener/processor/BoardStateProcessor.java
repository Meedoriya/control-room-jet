package com.alibi.control_room_jet.ship.listener.processor;

import com.alibi.control_room_jet.common.messages.BoardStateMessage;
import com.alibi.control_room_jet.common.processor.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BOARD_STATE")
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {
    @Override
    public void process(String jsonMessage) {
        //IGNORE
    }
}
