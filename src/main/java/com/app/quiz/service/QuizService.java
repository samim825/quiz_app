package com.app.quiz.service;

import com.app.quiz.entity.Question;
import com.app.quiz.entity.Quiz;
import com.app.quiz.repository.QuestionRepository;
import com.app.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public Quiz createQuiz(String category, int noOfQue, String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, noOfQue);
        quiz.setQuestions(questions);
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

}
