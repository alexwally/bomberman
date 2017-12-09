package ru.atom.gameserver.gamesession;

import ru.atom.gameserver.gamesession.tick.Tickable;

import static ru.atom.gameserver.gamesession.Replicator.writeReplica;

public class GameMechanics implements Tickable {

    @Override
    public void tick(long elapsed) {
        //read inputQueue
        //clear inputQueue
        //doMechanics()
        //новое состояние сразу кладется в реплику
        writeReplica();
    }

    private void doMechanics() {};
}
