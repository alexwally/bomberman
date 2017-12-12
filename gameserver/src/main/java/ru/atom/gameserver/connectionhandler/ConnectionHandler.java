package ru.atom.gameserver.connectionhandler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.atom.gameserver.gameservice.model.User;
import ru.atom.gameserver.gameservice.service.GameserviceService;
import ru.atom.gameserver.gamesession.InputQueue;
import ru.atom.gameserver.gamesession.model.Player;

import java.util.HashMap;

@Component
public class ConnectionHandler extends TextWebSocketHandler implements WebSocketHandler {

    @Autowired
    GameserviceService gameserviceService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("Socket Connected: " + session);

        HashMap<String, String> params = QueryProcessor.process(session.getUri().getQuery());
        ConnectionPool.getInstance().addPlayer(session, params.get("name"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received " + message.toString());

        Message inputMessage = JsonHelper.fromJson(message.getPayload(), Message.class);
        InputQueue.getInstance().offer(inputMessage);
    }

    public void sendMessage(Message replica) {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Socket Closed: [" + closeStatus.getCode() + "] " + closeStatus.getReason());
        super.afterConnectionClosed(session, closeStatus);

        ConnectionPool.getInstance().removePlayer(session);
    }

}
