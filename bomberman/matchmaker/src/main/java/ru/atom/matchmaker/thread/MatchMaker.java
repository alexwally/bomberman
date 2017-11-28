package ru.atom.matchmaker.thread;

import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.atom.matchmaker.client.MatchmakerClient;
import ru.atom.matchmaker.model.GameSession;
import ru.atom.matchmaker.model.User;
import ru.atom.matchmaker.service.MatchmakerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by sergey on 3/14/17.
 */
public class MatchMaker implements Runnable {
    private static final Logger log = LogManager.getLogger(MatchMaker.class);

    @Autowired
    private MatchmakerService matchmakerService;

    @Override
    public void run() {
        log.info("Started");
        List<User> candidates = new ArrayList<>(GameSession.PLAYERS_IN_GAME);
        Response response;
        Integer gameId = 0;
        User user = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //UserQueue.getInstance().iterator();
                user = UserQueue.getInstance().poll(10, TimeUnit.SECONDS);
                if (user != null) {
                    candidates.add(user);
                }
            } catch (InterruptedException e) {
                log.warn("Timeout reached");
            }

            if (candidates.size() == 1) {
                try {
                    response = MatchmakerClient.create(GameSession.PLAYERS_IN_GAME);
                    gameId = Integer.parseInt(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (user != null) {
                String name = user.getName();
                matchmakerService.saveUser(name, gameId);
            }

            if (candidates.size() == GameSession.PLAYERS_IN_GAME) {
                try {
                    MatchmakerClient.start(gameId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                candidates.clear();
            }
        }
    }
}
