package com.alibi.control_room_jet.common.bean;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Airport {
    private String name;
    private List<String> boards = new ArrayList<>();
    private int x;
    private int y;

    public void addBoard(String boardName) {
        if (!boards.contains(boardName)) {
            boards.add(boardName);
        }
    }

    public void removeBoard(String boardName) {
        boards.remove(boardName);
    }
}
