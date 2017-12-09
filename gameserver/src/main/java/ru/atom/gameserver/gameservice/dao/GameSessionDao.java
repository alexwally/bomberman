package ru.atom.gameserver.gameservice.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.gameserver.gameservice.model.GameSession;

public interface GameSessionDao extends CrudRepository<GameSession, Integer> {

}
