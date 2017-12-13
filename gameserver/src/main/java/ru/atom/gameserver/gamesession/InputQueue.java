package ru.atom.gameserver.gamesession;

import ru.atom.gameserver.connectionhandler.Message;
import ru.atom.gameserver.connectionhandler.MessageWrapper;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputQueue {
    private static Queue<MessageWrapper> instance = new ConcurrentLinkedQueue<>();

    public static Queue<MessageWrapper> getInstance() {
        return instance;
    }
}
