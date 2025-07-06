package com.leaderboard.custom_leaderboard.services;

import com.leaderboard.custom_leaderboard.entities.Competition;
import com.leaderboard.custom_leaderboard.entities.Participant;
import com.leaderboard.custom_leaderboard.repositories.CompetitionRepository;
import com.leaderboard.custom_leaderboard.repositories.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final CompetitionRepository competitionRepository;


    public Participant addParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public List<Participant> getParticipantsByCompetition(Long competitionId) {
        return participantRepository.findByCompetitionId(competitionId);
    }
    public Participant addParticipantToCompetition(Long competitionId, Participant participant) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        participant.setCompetition(competition);
        return participantRepository.save(participant);
    }
    public List<Participant> getLeaderboard(Long competitionId) {
        return participantRepository.findByCompetitionId(competitionId)
                .stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .toList();
    }
    public Participant updateParticipant(Long id, Participant updated) {
        Participant existing = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        existing.setName(updated.getName());
        existing.setScore(updated.getScore());
        return participantRepository.save(existing);
    }

    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new RuntimeException("Participant not found");
        }
        participantRepository.deleteById(id);
    }
    public List<Participant> getParticipantsByScoreRange(Integer minScore, Integer maxScore) {
        return participantRepository.findByScoreRange(minScore, maxScore);
    }
    public List<Participant> getParticipantsByCompetitionAndScore(Long competitionId, Integer minScore, Integer maxScore) {
        return participantRepository.findByCompetitionIdAndScoreRange(competitionId, minScore, maxScore);
    }

}
