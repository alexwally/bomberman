package ru.atom.gameserver.gameservice.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atom.gameserver.gameservice.dao.GameSessionDao;
import ru.atom.gameserver.gameservice.dao.UserDao;
import ru.atom.gameserver.gameservice.model.GameSession;
import ru.atom.gameserver.gameservice.model.User;

import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GameserviceService {
    private static AtomicInteger idGenerator = new AtomicInteger();

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameSessionDao gameSessionDao;

    @NotNull
    @Transactional
    public Integer createGameSession(@NotNull int playerCount) {
        Integer id = idGenerator.getAndIncrement();
        GameSession gameSession = new GameSession();
        gameSession.setId(id);
        gameSession.setPlayerCount(playerCount);
        gameSessionDao.save(gameSession);
        return id;
    }

    @Nullable
    @Transactional
    public User getLoggedIn(@NotNull String name) {
        return userDao.getByName(name);
    }
}
