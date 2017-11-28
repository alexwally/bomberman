package ru.atom.matchmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.atom.matchmaker.thread.MatchMaker;

@SpringBootApplication
public class MatchmakerApp {
    public static void main(String[] args) {
        SpringApplication.run(MatchmakerApp.class, args);

        Thread matchMaker = new Thread(new MatchMaker());
        matchMaker.setName("match-maker");
        matchMaker.start();
    }
}