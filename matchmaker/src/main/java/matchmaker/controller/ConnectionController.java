package matchmaker.controller;

import matchmaker.service.MatchmakerService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "matchmaker", method = RequestMethod.POST)
public class ConnectionController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    MatchmakerService matchmakerService;

    @RequestMapping(
            path = "join",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> join(@RequestParam("name") String name) {
        logger.info(name + " joins");
        Long gameId = matchmakerService.join(name);
        return new ResponseEntity<>(gameId.toString(), HttpStatus.OK);
    }
}
