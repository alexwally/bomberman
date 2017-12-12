package ru.atom.gameserver.gameservice.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.gameserver.gameservice.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
    User getByName(String name);
}
