package com.alibi.control_room_jet.office.listener.processors;

import com.alibi.control_room_jet.common.bean.Airport;
import com.alibi.control_room_jet.common.bean.Board;
import com.alibi.control_room_jet.common.bean.Route;
import com.alibi.control_room_jet.common.messages.AirportStateMessage;
import com.alibi.control_room_jet.common.messages.BoardStateMessage;
import com.alibi.control_room_jet.common.processor.MessageConverter;
import com.alibi.control_room_jet.common.processor.MessageProcessor;
import com.alibi.control_room_jet.office.provider.AirportsProvider;
import com.alibi.control_room_jet.office.provider.BoardsProvider;
import com.alibi.control_room_jet.office.service.WaitingRoutesService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BOARD_STATE")
@RequiredArgsConstructor
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {

    private final MessageConverter messageConverter;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final WaitingRoutesService waitingRoutesService;
    private final BoardsProvider boardsProvider;
    private final AirportsProvider airportsProvider;

    @Override
    public void process(String jsonMessage) {
        BoardStateMessage message = messageConverter.extractMessage(jsonMessage, BoardStateMessage.class);
        Board board = message.getBoard();
        Optional<Board> previousOpt = boardsProvider.getBoard(board.getName());
        Airport airport = airportsProvider.getAirport(board.getLocation());

        boardsProvider.addBoard(board);
        if (previousOpt.isPresent() && board.hasRoute() && !previousOpt.get().hasRoute()) {
            Route route = board.getRoute();
            waitingRoutesService.remove(route);
        }

        if (previousOpt.isEmpty() || !board.isBusy() && previousOpt.get().isBusy()) {
            airport.addBoard(board.getName());
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirportStateMessage(airport)));
        }
    }
}
