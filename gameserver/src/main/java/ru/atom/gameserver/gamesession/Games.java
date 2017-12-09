package ru.atom.gameserver.gamesession;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Games {
    private static Map<Integer, GameSession> map = new ConcurrentHashMap<>();

    public static void add(Integer id) {
        map.put(id, new GameSession(id));
    }

    public static Collection<GameSession> getAll() {
        return map.values();
    }
}