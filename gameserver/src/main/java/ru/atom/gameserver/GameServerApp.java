package ru.atom.gameserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import ru.atom.gameserver.gamesession.GameMechanicsThread;

@SpringBootApplication
public class GameServerApp {
    public static void main(String[] args) {
        SpringApplication.run(GameServerApp.class, args);
    }
}