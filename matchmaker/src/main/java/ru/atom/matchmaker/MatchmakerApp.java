package ru.atom.matchmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import ru.atom.matchmaker.service.MatchmakerService;
import ru.atom.matchmaker.thread.MatchMaker;

@SpringBootApplication
@ComponentScan
public class MatchmakerApp {
    @Autowired
    MatchmakerService matchmakerService;

    public static void main(String[] args) {
        SpringApplication.run(MatchmakerApp.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(new MatchMaker(matchmakerService));
            }
        };
    }
}