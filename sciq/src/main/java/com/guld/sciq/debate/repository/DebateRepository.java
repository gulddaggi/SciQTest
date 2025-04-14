package com.guld.sciq.debate.repository;

import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DebateRepository extends JpaRepository<Debate, Long> {
    List<Debate> findByStatus(DebateStatus status);
    List<Debate> findByScheduledStartTimeBetween(LocalDateTime start, LocalDateTime end);
    @Query("SELECT d FROM Debate d WHERE d.user.id = :userId AND d.status = :status")
    List<Debate> findByUserId(Long userId);
    List<Debate> findByScheduledStartTimeAfter(LocalDateTime time);
    List<Debate> findByStatusAndScheduledStartTimeBefore(DebateStatus status, LocalDateTime time);
}