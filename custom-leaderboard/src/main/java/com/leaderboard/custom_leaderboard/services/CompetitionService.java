package com.leaderboard.custom_leaderboard.services;

import com.leaderboard.custom_leaderboard.entities.Competition;
import com.leaderboard.custom_leaderboard.repositories.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Competition updateCompetition(Long id, Competition updated) {
        Competition existing = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        return competitionRepository.save(existing);
    }

    public void deleteCompetition(Long id) {
        if (!competitionRepository.existsById(id)) {
            throw new RuntimeException("Competition not found");
        }
        competitionRepository.deleteById(id);
    }

}
