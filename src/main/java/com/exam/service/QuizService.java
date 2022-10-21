package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public Set<Quiz> getQuizzes();
    public Quiz getQuiz(Long qid);
    public void deleteQuiz(Long qid);
    List<Quiz> getCategoryQuiz(Category category);
    List<Quiz> getActiveQuiz();
    List<Quiz> getActiveCategoryQuiz(Category category);
}
