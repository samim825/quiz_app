package com.app.quiz.service;

import com.app.quiz.entity.Question;
import com.app.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findQuestionsByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
}
