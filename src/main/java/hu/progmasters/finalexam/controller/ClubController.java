package hu.progmasters.finalexam.controller;

import hu.progmasters.finalexam.dto.ClubCreatedCommand;
import hu.progmasters.finalexam.dto.ClubInfo;
import hu.progmasters.finalexam.dto.ClubWinnerInfo;
import hu.progmasters.finalexam.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clubs")
@Slf4j
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<ClubInfo> save(@Valid @RequestBody ClubCreatedCommand command) {
        log.info("Http request, POST /api/clubs, body: " + command.toString());
        ClubInfo clubInfo = clubService.saveClub(command);
        return new ResponseEntity<>(clubInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{clubId}")
    public ResponseEntity<ClubWinnerInfo> update(@PathVariable("clubId") Integer id,
                                                 @Valid @RequestBody ClubCreatedCommand command) {
        log.info("Http request, PUT /api/clubs/{clubId} body: " + command.toString() +
                " with variable: " + id);
        ClubWinnerInfo updated = clubService.update(id, command);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
