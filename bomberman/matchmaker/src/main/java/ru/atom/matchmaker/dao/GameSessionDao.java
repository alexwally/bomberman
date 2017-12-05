package ru.atom.matchmaker.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.matchmaker.model.GameSession;

public interface GameSessionDao extends CrudRepository<GameSession, Integer> {
    GameSession getById(Integer id);
}
