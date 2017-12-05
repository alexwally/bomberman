package ru.atom.game_server.gameservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.atom.game_server.gameservice.service.GameserviceService;

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
    public ResponseEntity<String> join(@RequestParam("playerCount") int playerCount) {

        return ResponseEntity.ok().body(gameserviceService.createGameSession(playerCount).toString());
    }
}
