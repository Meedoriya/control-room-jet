package com.alibi.control_room_jet.office.provider;

import com.alibi.control_room_jet.common.bean.Airport;
import com.alibi.control_room_jet.common.bean.RoutePoint;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "application")
public class AirportsProvider {
    private final List<Airport> ports = new ArrayList<>();

    public Airport findAirportAndRemoveBoard(String boardName) {
        AtomicReference<Airport> result = new AtomicReference<>();
        ports.stream()
            .filter(airport -> airport.getBoards().contains(boardName))
            .findFirst()
            .ifPresent(airport -> {
                airport.removeBoard(boardName);
                result.set(airport);
            });
        return result.get();
    }

    public Airport getAirport(String airportName) {
        return ports.stream()
            .filter(airport -> airport.getName().equals(airportName))
            .findFirst()
            .orElse(new Airport());
    }

    public RoutePoint getRoutePoint(String airportName) {
        return new RoutePoint(getAirport(airportName));
    }
}
