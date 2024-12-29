package com.alibi.control_room_jet.common.processor;

import com.alibi.control_room_jet.common.messages.Message;
import com.google.gson.Gson;

public class MessageConverter {

    private final Gson gson = new Gson();

    public String extractCode(String data) {
        return gson.fromJson(data, Message.class).getCode();
    }

    public <T extends Message> T extractMessage(String data, Class<T> clazz) {
        return gson.fromJson(data, clazz);
    }

    public String toJson(Object message) {
        return gson.toJson(message);
    }
}
