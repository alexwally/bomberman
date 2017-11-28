package ru.atom.gameservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamesession", schema = "matchmaker")
public class GameSession {
    public static final int PLAYERS_IN_GAME = 2;

    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public GameSession setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '\'' + '}';
    }
}
