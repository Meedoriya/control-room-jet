package com.alibi.control_room_jet.office.job;

import com.alibi.control_room_jet.common.bean.Airport;
import com.alibi.control_room_jet.common.bean.Board;
import com.alibi.control_room_jet.common.bean.Route;
import com.alibi.control_room_jet.common.bean.RoutePath;
import com.alibi.control_room_jet.common.messages.AirportStateMessage;
import com.alibi.control_room_jet.common.messages.OfficeRouteMessage;
import com.alibi.control_room_jet.common.processor.MessageConverter;
import com.alibi.control_room_jet.office.provider.AirportsProvider;
import com.alibi.control_room_jet.office.provider.BoardsProvider;
import com.alibi.control_room_jet.office.service.PathService;
import com.alibi.control_room_jet.office.service.WaitingRoutesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class RouteDistributeJob {

    private final PathService pathService;
    private final BoardsProvider boardsProvider;
    private final WaitingRoutesService waitingRoutesService;
    private final AirportsProvider airportsProvider;

    private final MessageConverter messageConverter;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(initialDelay = 500, fixedDelay = 2500)
    private void distribute() {
        waitingRoutesService.list().stream()
            .filter(Route::notAssigned)
            .forEach(route -> {
                String startLocation = route.getPath().get(0).getFrom().getName();

                boardsProvider.getBoards().stream()
                    .filter(board -> startLocation.equals(board.getLocation()) && board.noBusy())
                    .findFirst()
                    .ifPresent(board -> sendBoardToRoute(route, board));

                if (route.notAssigned()) {
                    boardsProvider.getBoards().stream()
                        .filter(Board::noBusy)
                        .findFirst()
                        .ifPresent(board -> {
                            String currentLocation = board.getLocation();
                            if (currentLocation != startLocation) {
                                RoutePath routePath = pathService.makePath(currentLocation, startLocation);
                                route.getPath().add(0, routePath);
                            }
                            sendBoardToRoute(route, board);
                        });
                }
            });
    }

    private void sendBoardToRoute(Route route, Board board) {
        route.setBoardName(board.getName());
        Airport airport = airportsProvider.findAirportAndRemoveBoard(board.getName());
        board.setLocation(null);
        kafkaTemplate.sendDefault(messageConverter.toJson(new OfficeRouteMessage(route)));
        kafkaTemplate.sendDefault(messageConverter.toJson(new AirportStateMessage(airport)));
    }
}
