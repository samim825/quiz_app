package com.app.quiz.repository;

import com.app.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
