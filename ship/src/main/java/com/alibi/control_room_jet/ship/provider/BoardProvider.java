package com.alibi.control_room_jet.ship.provider;

import com.alibi.control_room_jet.common.bean.Board;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConfigurationProperties(prefix = "application")
public class BoardProvider {
    private final List<Board> boards = new ArrayList<>();

    public List<Board> getBoards() {
         return boards;
     }
}
