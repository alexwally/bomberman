package ru.atom.gameservice.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.gameservice.model.GameSession;

public interface GameSessionDao extends CrudRepository<GameSession, Integer> {

}
