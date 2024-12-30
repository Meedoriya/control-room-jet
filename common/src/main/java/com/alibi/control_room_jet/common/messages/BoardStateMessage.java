package com.alibi.control_room_jet.common.messages;

import com.alibi.control_room_jet.common.bean.Airport;
import com.alibi.control_room_jet.common.bean.Board;
import com.alibi.control_room_jet.common.bean.Source;
import com.alibi.control_room_jet.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardStateMessage extends Message {

    private Board board;

    public BoardStateMessage() {
        this.source = Source.BOARD;
        this.type = Type.STATE;
    }

    public BoardStateMessage(Board val) {
        this();
        this.board = val;
    }
}