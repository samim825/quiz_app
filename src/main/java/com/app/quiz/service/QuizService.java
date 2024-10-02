package com.app.quiz.service;

import com.app.quiz.dto.QuestionDto;
import com.app.quiz.dto.ResponseDto;
import com.app.quiz.entity.Question;
import com.app.quiz.entity.Quiz;
import com.app.quiz.repository.QuestionRepository;
import com.app.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<QuestionDto> getAllQuestionsByQuiz(Long id) {
        List<Question> questionList = quizRepository.findById(id).get().getQuestions();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setTitle(question.getTitle());
            questionDto.setId(question.getId());
            questionDto.setOption1(question.getOption1());
            questionDto.setOption2(question.getOption2());
            questionDto.setOption3(question.getOption3());
            questionDto.setOption4(question.getOption4());
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public Integer calculateResult(Long id, List<ResponseDto> responseDtoList) {
        List<Question> questionList = quizRepository.findById(id).get().getQuestions();
        int i=0;
        int result = 0;
        for(ResponseDto responseDto : responseDtoList) {
            if(responseDto.getAnswer().equals(questionList.get(i).getAnswer())) {
                result ++;
            }
            i++;
        }
        return result;
    }

}
