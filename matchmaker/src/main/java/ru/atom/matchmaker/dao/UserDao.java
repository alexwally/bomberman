package ru.atom.matchmaker.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.matchmaker.model.User;

import javax.persistence.Entity;

public interface UserDao extends CrudRepository<User, Integer> {
    User getByName(String name);
}
