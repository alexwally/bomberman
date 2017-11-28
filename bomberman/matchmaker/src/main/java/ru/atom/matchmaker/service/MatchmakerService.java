package ru.atom.matchmaker.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atom.matchmaker.dao.GameSessionDao;
import ru.atom.matchmaker.dao.UserDao;
import ru.atom.matchmaker.model.User;

import javax.transaction.Transactional;

@Service
public class MatchmakerService {
    @Autowired
    private UserDao userDao;
    private GameSessionDao gameSessionDao;

    @NotNull
    @Transactional
    public void saveUser(@NotNull String name, @NotNull Integer gameId) {
        User user = new User();
        user.setName(name);
        user.setGs(gameSessionDao.getById(gameId));
        userDao.save(user);
    }

    @Nullable
    @Transactional
    public User getLoggedIn(@NotNull String name) {
        return userDao.getByName(name);
    }
}
