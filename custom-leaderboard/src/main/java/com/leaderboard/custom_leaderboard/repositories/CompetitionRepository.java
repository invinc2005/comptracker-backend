package com.leaderboard.custom_leaderboard.repositories;

import com.leaderboard.custom_leaderboard.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
