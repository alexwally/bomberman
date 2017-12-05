package ru.atom.matchmaker.thread;

import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.atom.matchmaker.client.MatchmakerClient;
import ru.atom.matchmaker.model.GameSession;
import ru.atom.matchmaker.model.User;
import ru.atom.matchmaker.service.MatchmakerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 3/14/17.
 */

public class MatchMaker implements Runnable {
    private static final Logger log = LogManager.getLogger(MatchMaker.class);

    private MatchmakerService matchmakerService;

    public MatchMaker(MatchmakerService matchmakerService) {
        this.matchmakerService = matchmakerService;
    }

    @Override
    public void run() {
        log.info("Started");
        List<User> candidates = new ArrayList<>(GameSession.PLAYERS_IN_GAME);
        Response response = null;
        Integer gameId = 0;
        User user = null;
        String name = "";
        while (!Thread.currentThread().isInterrupted()) {
            try {
                user = UserQueue.getInstance().take();//poll(10_000, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("Timeout reached");
            }

            if (user != null) {
                candidates.add(user);

                if (candidates.size() == 1) {
                    try {
                        response = MatchmakerClient.create(GameSession.PLAYERS_IN_GAME);
                        gameId = Integer.parseInt(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                name = user.getName();
                matchmakerService.saveUser(name, gameId);

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
}
