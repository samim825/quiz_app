package com.app.quiz.controller;

import com.app.quiz.dto.QuestionDto;
import com.app.quiz.dto.ResponseDto;
import com.app.quiz.entity.Quiz;
import com.app.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category,
                                     @RequestParam int noOfQue,
                                     @RequestParam String title) {

        try {
            Quiz quiz = quizService.createQuiz(category, noOfQue, title);
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating quiz "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/all-question/quiz-id/{id}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionByQuiz(@PathVariable Long id) {
        List<QuestionDto>  questionDtos = quizService.getAllQuestionsByQuiz(id);
        if(questionDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @PostMapping("/submit-quiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id,  @RequestBody List<ResponseDto> responseDtos) {
        Integer result = quizService.calculateResult(id, responseDtos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
