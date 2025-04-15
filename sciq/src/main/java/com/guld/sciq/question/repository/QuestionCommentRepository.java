package com.guld.sciq.question.repository;

import com.guld.sciq.question.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Integer> {
    List<QuestionComment> findByQuestionId(Long questionId);
    List<QuestionComment> findByUserId(Long userId);
} 