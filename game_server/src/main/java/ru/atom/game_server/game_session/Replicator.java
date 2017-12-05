package ru.atom.game_server.game_session;

import ru.atom.game_server.connection_handler.Broker;
import ru.atom.game_server.connection_handler.Topic;
import ru.atom.game_server.game_session.model.Movable;
import ru.atom.game_server.game_session.model.Player;
import ru.atom.game_server.game_session.model.Tickable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Replicator {
    private static Set<Tickable> tickables = new ConcurrentSkipListSet<>();
    private static Set<Player> players = new ConcurrentSkipListSet<>();
    private static class Data {
        public List objects = new ArrayList<Object>();
        boolean gameOver;

        public List getObjects() {
            return objects;
        }
        public void setGameOver(boolean gameOver) {
            this.gameOver = gameOver;
        }
    }

    public void registerTickable(Movable tickable) {
        tickables.add(tickable);
    }
    public void unregisterTickable(Movable tickable) {
        tickables.remove(tickable);
    }

    public void registerPlayer(Player player) {
        tickables.add(player);
    }
    public void unregisterPlayer(Player player) {
        tickables.remove(player);
    }

    public static void writeReplica() {

        for (Player player : players) {
            Data data = new Data();
            tickables.forEach(tickable -> data.getObjects().add(tickable));
            data.setGameOver(player.isGameOver());

            Broker.getInstance().send(player.getName(), Topic.REPLICA, data);
        }

    }
}
