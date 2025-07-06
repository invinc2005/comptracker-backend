package com.leaderboard.custom_leaderboard.controllers;

import com.leaderboard.custom_leaderboard.entities.Participant;
import com.leaderboard.custom_leaderboard.services.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ResponseEntity<?> addParticipant(@Valid @RequestBody Participant participant) {
        Participant saved = participantService.addParticipant(participant);
        return ResponseEntity.ok(saved);
    }


    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants(
            @RequestParam(required = false) Integer minScore,
            @RequestParam(required = false) Integer maxScore
    ) {
        if (minScore != null || maxScore != null) {
            return ResponseEntity.ok(participantService.getParticipantsByScoreRange(minScore, maxScore));
        }
        return ResponseEntity.ok(participantService.getAllParticipants());
    }


    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<Participant>> getParticipantsByCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(participantService.getParticipantsByCompetition(competitionId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id,@Valid @RequestBody Participant participant) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-competition-and-score")
    public ResponseEntity<List<Participant>> getParticipantsByCompetitionAndScore(
            @RequestParam Long competitionId,
            @RequestParam(required = false) Integer minScore,
            @RequestParam(required = false) Integer maxScore) {
        return ResponseEntity.ok(
                participantService.getParticipantsByCompetitionAndScore(competitionId, minScore, maxScore)
        );
    }

}
