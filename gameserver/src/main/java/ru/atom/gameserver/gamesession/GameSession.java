package ru.atom.gameserver.gamesession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.atom.gameserver.connectionhandler.ConnectionPool;
import ru.atom.gameserver.gamesession.model.Player;
import ru.atom.gameserver.gamesession.model.Tickable;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class GameSession {
    private static final Logger log = LogManager.getLogger(GameSession.class);

    public Set<Tickable> tickables = new ConcurrentSkipListSet<>();
    public Set<Player> players = new ConcurrentSkipListSet<>();

    private final Integer id;

    public GameSession(Integer id) {
        this.id = id;

        Collection<String> players = ConnectionPool.getInstance().getPlayers();
        for (String player : players) {
            this.players.add(new Player(player));
        }
    }

    public Player getByName(String name) {
        for (Player player : players) {
            if (player.getName() == name) return player;
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void addPlayer(String name) { }

    public void start(Integer id) { }

    public void registerTickable(Tickable tickable) {
        tickables.add(tickable);
    }

    public void unregisterTickable(Tickable tickable) {
        tickables.remove(tickable);
    }

    public void registerPlayer(Player player) { players.add(player); }

    public void unregisterPlayer(Player player) { players.add(player); }

    @Override
    public String toString() {
        return "GameSession{" +
                //"connections=" + Arrays.toString(connections) +
                ", id=" + id +
                '}';
    }
}
