package com.alibi.control_room_jet.common.processor;

import com.alibi.control_room_jet.common.messages.Message;

public interface MessageProcessor <T extends Message> {
    void process(String jsonMessage);
}
