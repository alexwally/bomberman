package ru.atom.gameserver.gamesession;

import java.util.Optional;

public class GameMechanicsThread implements Runnable {

    private Integer id;

    @Override
    public void run() {
        GameSession gameSession = new GameSession(id);
        Games.addGameSession(id, Optional.of(gameSession));

        while (!Thread.currentThread().isInterrupted()) {

        }
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
