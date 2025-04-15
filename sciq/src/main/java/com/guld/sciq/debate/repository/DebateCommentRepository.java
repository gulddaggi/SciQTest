package com.guld.sciq.debate.repository;

import com.guld.sciq.debate.entity.DebateComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebateCommentRepository extends JpaRepository<DebateComment, Long> {
    List<DebateComment> findByDebateId(Long debateId);
    List<DebateComment> findByUserId(Long userId);
} 