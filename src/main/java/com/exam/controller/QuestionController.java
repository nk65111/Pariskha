package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return  ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    @GetMapping("/{quesId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("quesId") Long quesId){
        return ResponseEntity.ok(this.questionService.getQuestion(quesId));
    }

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestions(@PathVariable("qid") Long qid){
        Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestions();
        List<Question> al=new ArrayList<>(questions);
        if(al.size()>Integer.parseInt(quiz.getNumberOfQuestion())){
            al=al.subList(0,Integer.parseInt(quiz.getNumberOfQuestion())+1);
        }
        for(Question question:al){
            question.setAnswer("");
        }
        Collections.shuffle(al);
        return ResponseEntity.ok(al);
    }


    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getAllQuestions(@PathVariable("qid") Long qid){
        Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestions();
        List<Question> al=new ArrayList<>(questions);
        return ResponseEntity.ok(al);
    }

    @PostMapping("/eval-question")
    public ResponseEntity<?> EvalQuestion(@RequestBody List<Question> questions){
        Integer attempted=0;
        Integer correctAns=0;
        Double marksGot=0d;
        for(Question question:questions){
            Question dbQuestion=this.questionService.get(question.getQuesId());
            if(dbQuestion.getAnswer().equals(question.getGivenAnswer())){
                correctAns++;
            }
            if(question.getGivenAnswer()!=null&&question.getGivenAnswer().length()!=0){
                attempted++;
            }
        }

        //let singleQuestionMarks=this.questions[0].quiz.maxMarks/this.questions[0].quiz.numberOfQuestion;
        //   this.marksGot+=singleQuestionMarks*this.correctAns;
        Double singleQuestionMark= Double.valueOf(questions.get(0).getQuiz().getMaxMarks())/Integer.parseInt(questions.get(0).getQuiz().getNumberOfQuestion());
        marksGot+=singleQuestionMark*correctAns;

        HashMap<String,Object> map= new HashMap<>();
        map.put("attempted",attempted);
        map.put("correctAns",correctAns);
        map.put("marksGot",marksGot);
     

        return  ResponseEntity.ok(map);
    }
}
