package com.guld.sciq.debate.repository;

import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DebateRepository extends JpaRepository<Debate, Long> {
    List<Debate> findByUserId(Long userId);
    List<Debate> findByScienceDiscipline(ScienceDisciplineType scienceDiscipline);
    List<Debate> findByStatus(DebateStatus status);
    List<Debate> findByScheduledStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Debate> findByScheduledStartTimeAfter(LocalDateTime time);
    List<Debate> findByStatusAndScheduledStartTimeBefore(DebateStatus status, LocalDateTime time);
}