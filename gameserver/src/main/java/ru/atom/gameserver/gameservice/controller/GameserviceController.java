package ru.atom.gameserver.gameservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.atom.gameserver.gameservice.service.GameserviceService;
import ru.atom.gameserver.gamesession.GameMechanics;
import ru.atom.gameserver.gamesession.Games;

import java.util.Optional;

@Controller
@RequestMapping("/game")
public class GameserviceController {

    @Autowired
    private GameserviceService gameserviceService;

    @RequestMapping(
            path = "create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> gameCreate(@RequestParam("playerCount") int playerCount) {
        Integer gameId = gameserviceService.createGameSession(playerCount);
        Games.createGame(gameId, Optional.empty());

        return ResponseEntity.ok().body(gameId.toString());
    }

    @RequestMapping(
            path = "start",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> gameStart(@RequestParam("gameId") Integer gameId) {
        if (Games.containsGame(gameId)) {
            GameMechanics gameMechanics = new GameMechanics();
            gameMechanics.setId(gameId);
            Thread gameMechanicsThread = new Thread(gameMechanics);
            gameMechanicsThread.setName("game-mechanics-" + gameId);
            gameMechanicsThread.start();
        } else {
            return ResponseEntity.badRequest().body("Game with that Id does not exist");
        }

        return ResponseEntity.ok().body(gameId.toString());
    }
}
