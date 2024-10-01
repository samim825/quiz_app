package com.app.quiz.repository;

import com.app.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionsByCategory(String category);

    @Query(value = """
        select *from Question  q 
                where q.category = :category 
                ORDER BY RANDOM() LIMIT :noOfQue
        """, nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noOfQue);
}
