package hu.progmasters.finalexam.controller;

import hu.progmasters.finalexam.dto.AllPlayerInfo;
import hu.progmasters.finalexam.dto.PlayerCreatedCommand;
import hu.progmasters.finalexam.dto.PlayerInfo;
import hu.progmasters.finalexam.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Slf4j
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerInfo> save(@Valid @RequestBody PlayerCreatedCommand command) {
        log.info("Http request, POST /api/players, body: " + command.toString());
        PlayerInfo playerInfo = playerService.savePlayer(command);
        return new ResponseEntity<>(playerInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AllPlayerInfo>> findAllPlayers() {
        log.info("Http request, GET /api/players");
        List<AllPlayerInfo> players = playerService.listPlayer();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PutMapping("/{playerId}/club/{clubId}")
    public ResponseEntity<PlayerInfo> update(@PathVariable int playerId, int clubId,
                                             @Valid @RequestBody PlayerCreatedCommand command) {
        log.info("Http request, PUT /api/players/{playerId}/club/{clubId}: " + command.toString());
        PlayerInfo updated = playerService.update(playerId, clubId, command);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
