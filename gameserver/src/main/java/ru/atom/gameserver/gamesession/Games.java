package ru.atom.gameserver.gamesession;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Games {
    private static Map<Integer, Optional<GameSession>> map = new ConcurrentHashMap<>();

    public static void createGame(Integer id, Optional<GameSession> gameSession) {
        map.put(id, gameSession);
    }

    public static void addGameSession(Integer id, Optional<GameSession> gameSession) { map.replace(id, gameSession); }

    public static boolean containsGame(Integer id) { return map.containsKey(id); }

    public static Collection<Optional<GameSession>> getAll() {
        return map.values();
    }
}