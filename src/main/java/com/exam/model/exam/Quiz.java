package com.exam.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quizes")
@Getter
@Setter
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qid;
    private String title;
    private  String description;
    private String maxMarks;
    private String numberOfQuestion;
    private Boolean active=false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "quiz")
    @JsonIgnore
    private Set<Question> questions=new HashSet<>();

}
