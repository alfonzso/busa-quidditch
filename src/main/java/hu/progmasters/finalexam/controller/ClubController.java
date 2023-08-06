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

    @GetMapping("/superstar/{id}")
    public ResponseEntity<String> getSupStar(@PathVariable Integer id) {
        log.info("Http request, Get /superstar/{supId} body: " + id);
        return new ResponseEntity<>(clubService.hasClubSupStar(id) ? "Has a superstar player!" : "No superstar player.", HttpStatus.OK);
    }

    @PutMapping("/{clubId}")
    public ResponseEntity<ClubWinnerInfo> update(@PathVariable Integer clubId) {
        log.info("Http request, PUT /api/clubs/{clubId} with variable: " + clubId);
        ClubWinnerInfo updated = clubService.update(clubId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
