package hu.progmasters.finalexam.controller;

import hu.progmasters.finalexam.dto.ClubStatistics;
import hu.progmasters.finalexam.service.CoachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coaches")
@Slf4j
public class CoachController {
    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> delete(@PathVariable("coachId") Integer coachId) {
        log.info("Http request, DELETE //api/coaches/{coachId} with variable: " + coachId);
        coachService.delete(coachId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/statistics/{coachId}")
    public ResponseEntity<ClubStatistics> createStatistics(@PathVariable int coachId) {
        log.info("Http request, GET /api/coaches/statistics/{coachId}");
        ClubStatistics listPlayers = coachService.listStatistics(coachId);
        return new ResponseEntity<>(listPlayers, HttpStatus.OK);
    }
}
