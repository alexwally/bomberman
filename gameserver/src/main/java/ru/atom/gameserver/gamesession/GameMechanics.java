package ru.atom.gameserver.gamesession;

import org.slf4j.LoggerFactory;
import ru.atom.gameserver.connectionhandler.Message;
import ru.atom.gameserver.connectionhandler.MessageWrapper;
import ru.atom.gameserver.connectionhandler.Topic;
import ru.atom.gameserver.gamesession.model.Bomb;
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
    GameSession gameSession;
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
        //act(FRAME_TIME);
        for (MessageWrapper action : actions) {
            if (action.getMessage().getTopic() == Topic.PLANT_BOMB) {
                gameSession.tickables.add(new Bomb(action.getPlayer().getPosition().getX(),
                        action.getPlayer().getPosition().getY()));
            } else
                if (action.getMessage().getTopic() == Topic.MOVE) {

                } else { }
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

    private void act(long elapsed) {
        gameSession.tickables.forEach(tickable -> tickable.tick(elapsed));
    }

    public long getTickNumber() {
        return tickNumber;
    }
}
