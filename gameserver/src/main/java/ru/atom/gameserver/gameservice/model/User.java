package ru.atom.gameserver.gameservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "matchmaker")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private GameSession gameSession;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public User setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", game session='" + gameSession + '\'' +
                '}';
    }
}
