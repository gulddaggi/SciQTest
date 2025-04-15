package com.guld.sciq.debate.comment.repository;

import java.util.List;

import com.guld.sciq.debate.comment.entity.DebateComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebateCommentRepository extends JpaRepository<DebateComment, Long> {
    List<DebateComment> findByDebateId(Long debateId);
    List<DebateComment> findByUserId(Long userId);
} 