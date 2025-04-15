package com.guld.sciq.question.repository;

import com.guld.sciq.question.entity.Question;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUserId(Long userId);
    List<Question> findByScienceDiscipline(ScienceDisciplineType scienceDiscipline);
    
    @Query("SELECT q FROM Question q ORDER BY q.recommendCnt DESC")
    List<Question> findTopRatedQuestions(@Param("limit") int limit);
    
    @Query("SELECT q FROM Question q ORDER BY q.createdAt DESC")
    List<Question> findRecentQuestions(@Param("limit") int limit);
} 