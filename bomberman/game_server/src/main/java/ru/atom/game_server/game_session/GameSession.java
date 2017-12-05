package ru.atom.game_server.game_session;

import org.apache.log4j.LogManager;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class GameSession {
    private static final Logger log = LogManager.getLogger(MatchMaker.class);

    //public static final int PLAYERS_IN_GAME = 4;

    private final Integer id;
    private final Connection[] connections; //?

    public GameSession(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "connections=" + Arrays.toString(connections) +
                ", id=" + id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void addPlayer(String name) {

    }

    public void start(Integer id) {

    }
}
