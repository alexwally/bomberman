package ru.atom.gameservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atom.gameservice.dao.GameSessionDao;
import ru.atom.gameservice.model.GameSession;

import javax.transaction.Transactional;

@Service
public class GameserviceService {
    private static Integer nextId = 0;

    @Autowired
    private GameSessionDao gameSessionDao;

    @NotNull
    @Transactional
    public Integer createGameSession(@NotNull int playerCount) {
        Integer id = nextId;
        nextId++;
        GameSession gameSession = new GameSession();
        gameSession.setId(id);
        gameSessionDao.save(gameSession);
        return id;
    }
}
