package ru.atom.gameserver.gamesession;

import org.slf4j.LoggerFactory;
import ru.atom.gameserver.connectionhandler.Message;
import ru.atom.gameserver.connectionhandler.MessageWrapper;
import ru.atom.gameserver.connectionhandler.Topic;
import ru.atom.gameserver.gamesession.model.Bomb;
import ru.atom.gameserver.gamesession.model.Movable;
import ru.atom.gameserver.gamesession.model.Player;
import ru.atom.gameserver.gamesession.model.Tickable;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class GameMechanics implements Runnable {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GameMechanics.class);
    private static final int FPS = 60;
    private static final long FRAME_TIME = 1000 / FPS;
    private long tickNumber = 0;

    private Integer id;
    private static GameSession gameSession;
    private List<MessageWrapper> actions = new ArrayList<>();
    private Queue<MessageWrapper> inputQueue = InputQueue.getInstance();

    @Override
    public void run() {
        gameSession = new GameSession(id);
        Games.addGameSession(id, Optional.of(gameSession));

        while (!Thread.currentThread().isInterrupted()) {
            for (MessageWrapper action : inputQueue) {
                actions.add(action);
            }
            inputQueue.clear();
            doMechanics();
            Replicator.writeReplica();
        }
    }

    private void doMechanics() {
        long started = System.currentTimeMillis();
        for (MessageWrapper action : actions) {
            boolean alreadyMove = false;

            for (Tickable tickable : gameSession.tickables)
                tickable.tick(FRAME_TIME);

            if (action.getMessage().getTopic() == Topic.PLANT_BOMB) {
                int amountOfBombs = action.getPlayer().getBombs();
                if (amountOfBombs != 0) {
                    gameSession.tickables.add(new Bomb(action.getPlayer().getPosition().getX(),
                            action.getPlayer().getPosition().getY()));
                    action.getPlayer().setBombs(amountOfBombs-1);
                }
            }

            if (action.getMessage().getTopic() == Topic.MOVE) {
                if (alreadyMove = false) {
                    action.getPlayer().move(Movable.Direction.valueOf(action.getMessage().getData()), FRAME_TIME);
                }
                alreadyMove = true;
            }
        }
        long elapsed = System.currentTimeMillis() - started;
        if (elapsed < FRAME_TIME) {
            log.info("All tick finish at {} ms", elapsed);
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(FRAME_TIME - elapsed));
        } else {
            log.warn("tick lag {} ms", elapsed - FRAME_TIME);
        }
        log.info("{}: tick ", tickNumber);
        tickNumber++;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static GameSession getGameSession() {
        return gameSession;
    }

    public long getTickNumber() {
        return tickNumber;
    }
}
