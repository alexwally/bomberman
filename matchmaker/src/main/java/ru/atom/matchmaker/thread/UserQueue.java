package ru.atom.matchmaker.thread;

import ru.atom.matchmaker.model.User;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sergey on 3/14/17.
 */
public class UserQueue {
    private static BlockingQueue<User> instance = new LinkedBlockingQueue<>();

    public static BlockingQueue<User> getInstance() {
        return instance;
    }
}
