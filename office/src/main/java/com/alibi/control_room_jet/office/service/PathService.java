package com.alibi.control_room_jet.office.service;

import com.alibi.control_room_jet.common.bean.Route;
import com.alibi.control_room_jet.common.bean.RoutePath;
import com.alibi.control_room_jet.common.bean.RoutePoint;
import com.alibi.control_room_jet.office.provider.AirportsProvider;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PathService {
    private final AirportsProvider airportsProvider;

    public RoutePath makePath(String from, String to) {
        return new RoutePath(airportsProvider.getRoutePoint(from), airportsProvider.getRoutePoint(to), 0);
    }

    public Route convertLocationsToRoute(List<String> locations) {
        Route route = new Route();
        List<RoutePath> path = new ArrayList<>();
        List<RoutePoint> points = new ArrayList<>();

        locations.forEach(location -> {
            airportsProvider.getPorts().stream()
                .filter(airport -> airport.getName().equals(location))
                .findFirst()
                .ifPresent(airport -> points.add(new RoutePoint(airport)));
        });

        for (int i = 0; i < points.size() - 1; i++) {
            path.add(new RoutePath());
        }

        path.forEach(row -> {
            int index = path.indexOf(row);
            if (row.getFrom() == null) {
                row.setFrom(points.get(index));
                if (points.size() > index + 1) {
                    row.setTo(points.get(index + 1));
                } else {
                    row.setTo(points.get(index));
                }
            }
        });

        route.setPath(path);

        return route;
    }
}
