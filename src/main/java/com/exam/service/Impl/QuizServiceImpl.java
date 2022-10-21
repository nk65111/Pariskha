package com.exam.service.Impl;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.repository.QuizRepository;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long qid) {
        return this.quizRepository.findById(qid).get();
    }

    @Override
    public void deleteQuiz(Long qid) {
         this.quizRepository.deleteById(qid);
    }

    @Override
    public List<Quiz> getCategoryQuiz(Category category) {
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuiz() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveCategoryQuiz(Category category) {
        return this.quizRepository.findByCategoryAndActive(category,true);
    }
}
