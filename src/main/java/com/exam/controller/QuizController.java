package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return  ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @GetMapping("/{qid}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("qid") Long qid){
        return ResponseEntity.ok(this.quizService.getQuiz(qid));
    }

    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid){
        this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public ResponseEntity<List<Quiz>> getCategoryQuiz(@PathVariable("cid") Long cid){
        Category category=new Category();
        category.setCid(cid);
        return ResponseEntity.ok(this.quizService.getCategoryQuiz(category));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Quiz>> getActiveQuiz(){
        return ResponseEntity.ok(this.quizService.getActiveQuiz());
    }

    @GetMapping("/category/active/{cid}")
    public ResponseEntity<List<Quiz>> getCategoryActiveQuiz(@PathVariable("cid") Long cid){
        Category category=new Category();
        category.setCid(cid);
        return ResponseEntity.ok(this.quizService.getActiveCategoryQuiz(category));
    }

}
