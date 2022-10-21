package com.exam.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long cid;

    private String title;

    @Column(length = 5000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="category")
    @JsonIgnore
    private Set<Quiz> quizzes=new LinkedHashSet<>();
}
