package ru.atom.matchmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.atom.matchmaker.model.User;
import ru.atom.matchmaker.service.MatchmakerService;
import ru.atom.matchmaker.thread.UserQueue;

import java.io.IOException;

import static java.lang.Thread.sleep;

@Controller
@RequestMapping("/matchmaker")
public class ConnectionController {

    @Autowired
    private MatchmakerService matchmakerService;

    @RequestMapping(
            path = "join",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> join(@RequestParam("name") String name) throws IOException, InterruptedException {
        if (name.length() > 20) {
            return ResponseEntity.badRequest()
                    .body("Too long name");
        }

        User alreadyLoggedIn = matchmakerService.getLoggedIn(name);
        if (alreadyLoggedIn != null) {
            return ResponseEntity.badRequest()
                    .body("Already logged in");
        }

        User user = new User();
        user.setName(name);
        UserQueue.getInstance().offer(user);

        while (matchmakerService.getLoggedIn(name) == null) {
            sleep(100);
        }

        return ResponseEntity.ok().body(matchmakerService.getLoggedIn(name).getGs().getId().toString());
    }
}
