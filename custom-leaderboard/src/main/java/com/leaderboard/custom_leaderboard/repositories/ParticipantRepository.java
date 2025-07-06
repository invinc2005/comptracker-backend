package com.leaderboard.custom_leaderboard.repositories;

import com.leaderboard.custom_leaderboard.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByCompetitionId(Long competitionId);
    @Query("SELECT p FROM Participant p WHERE " +
            "(:minScore IS NULL OR p.score >= :minScore) AND " +
            "(:maxScore IS NULL OR p.score <= :maxScore)")
    List<Participant> findByScoreRange(
            @Param("minScore") Integer minScore,
            @Param("maxScore") Integer maxScore
    );
    @Query("SELECT p FROM Participant p WHERE p.competition.id = :competitionId " +
            "AND (:minScore IS NULL OR p.score >= :minScore) " +
            "AND (:maxScore IS NULL OR p.score <= :maxScore)")
    List<Participant> findByCompetitionIdAndScoreRange(
            @Param("competitionId") Long competitionId,
            @Param("minScore") Integer minScore,
            @Param("maxScore") Integer maxScore
    );
}
