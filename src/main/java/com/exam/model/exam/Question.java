package com.exam.model.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;

    @Column(length = 5000)
    private String content;

    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    @Transient
    private  String givenAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
