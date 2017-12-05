package ru.atom.game_server.game_session;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class Games {
    private static ConcurrentHashMap<Long, GameSession> map = new ConcurrentHashMap<>();

    public static void add(Integer id) {
        map.put(id, new GameSession());
    }

    public static Collection<GameSession> getAll() {
        return map.values();
    }
}
