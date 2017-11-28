package ru.atom.matchmaker.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.matchmaker.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
    User getByName(String name);
}
