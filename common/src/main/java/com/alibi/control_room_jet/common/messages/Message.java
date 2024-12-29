package com.alibi.control_room_jet.common.messages;

import com.alibi.control_room_jet.common.bean.Source;
import com.alibi.control_room_jet.common.bean.Type;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {
    protected Type type;
    protected Source source;

    public String getCode() {
        return source.name() + "_" + type.name();
    }
}
