package com.alibi.control_room_jet.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutePoint {
    private String name;
    private double x;
    private double y;

    public RoutePoint(Airport airport) {
        this.name = airport.getName();
        this.x = airport.getX();
        this.y = airport.getY();
    }
}
