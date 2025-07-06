package com.leaderboard.custom_leaderboard.controllers;

import com.leaderboard.custom_leaderboard.entities.Competition;
import com.leaderboard.custom_leaderboard.entities.Participant;
import com.leaderboard.custom_leaderboard.services.CompetitionService;
import com.leaderboard.custom_leaderboard.services.ParticipantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.modeler.ParameterInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final ParticipantService participantService;


    @PostMapping
    public ResponseEntity<Competition> createCompetition(@Valid @RequestBody Competition competition) {
//        System.out.println("RECEIVED: " + competition);
//        System.out.println("Start Date: " + competition.getStartDate());
//        System.out.println("End Date: " + competition.getEndDate());
        return ResponseEntity.ok(competitionService.createCompetition(competition));
    }

    @GetMapping
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        return ResponseEntity.ok(competitionService.getAllCompetitions());
    }

    @PostMapping("/{competitionId}/participants")
    public ResponseEntity<Participant> addParticipant(
            @PathVariable Long competitionId,
            @RequestBody Participant participant) {
        Participant saved = participantService.addParticipantToCompetition(competitionId, participant);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{competitionId}/participants")
    public ResponseEntity<List<Participant>> getParticipantsForCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(participantService.getParticipantsByCompetition(competitionId));
    }

    @GetMapping("/{competitionId}/leaderboard")
    public ResponseEntity<List<Participant>> getLeaderboard(@PathVariable Long competitionId) {
        return ResponseEntity.ok(participantService.getLeaderboard(competitionId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(@PathVariable Long id,@Valid    @RequestBody Competition competition) {
        return ResponseEntity.ok(competitionService.updateCompetition(id, competition));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }


}
