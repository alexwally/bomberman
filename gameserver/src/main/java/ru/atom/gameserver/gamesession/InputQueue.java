package ru.atom.gameserver.gamesession;

import ru.atom.gameserver.connectionhandler.Message;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputQueue {
    private static Queue<Message> instance = new ConcurrentLinkedQueue<>();

    public static Queue<Message> getInstance() {return instance;}
}
