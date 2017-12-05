package ru.atom.game_server.gameservice.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.game_server.gameservice.model.GameSession;

public interface GameSessionDao extends CrudRepository<GameSession, Integer> {

}
