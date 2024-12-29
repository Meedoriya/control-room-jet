package com.alibi.control_room_jet.common.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class RoutePath {
    private RoutePoint from;
    private RoutePoint to;
    private double progress;

    private void addProgress(double speed) {
        progress += speed;
        if (progress > 100) {
            progress = 100;
        }
    }

    private boolean inProgress() {
        return progress < 100;
    }

    private boolean done() {
        return progress == 100;
    }
}
