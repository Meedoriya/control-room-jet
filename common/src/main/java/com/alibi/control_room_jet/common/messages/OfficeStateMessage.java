package com.alibi.control_room_jet.common.messages;

import com.alibi.control_room_jet.common.bean.Source;
import com.alibi.control_room_jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeStateMessage extends Message {

    public OfficeStateMessage() {
        this.source = Source.OFFICE;
        this.type = Type.STATE;
    }
}
