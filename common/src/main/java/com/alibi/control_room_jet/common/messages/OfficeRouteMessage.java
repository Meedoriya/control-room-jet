package com.alibi.control_room_jet.common.messages;

import com.alibi.control_room_jet.common.bean.Route;
import com.alibi.control_room_jet.common.bean.Source;
import com.alibi.control_room_jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeRouteMessage extends Message {

    private Route route;

    public OfficeRouteMessage() {
        this.source = Source.OFFICE;
        this.type = Type.STATE;
    }

    public OfficeRouteMessage(Route val) {
        this();
        this.route = val;
    }
}
