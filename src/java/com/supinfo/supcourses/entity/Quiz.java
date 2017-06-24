package com.supinfo.supcourses.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Yicong
 */
@Entity
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean pass;
    private String quizContent;
    private Boolean takeQuiz;
    
    @ManyToOne
    @JoinColumn(name = "STUDENT_FK")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "COURSE_FK")
    private Course course;

    public Quiz() {
    }

    public Quiz(Boolean pass, String quizContent, Boolean takeQuiz, Student student, Course course) {
        this.pass = pass;
        this.quizContent = quizContent;
        this.takeQuiz = takeQuiz;
        this.student = student;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTakeQuiz() {
        return takeQuiz;
    }

    public void setTakeQuiz(Boolean takeQuiz) {
        this.takeQuiz = takeQuiz;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getQuizContent() {
        return quizContent;
    }

    public void setQuizContent(String quizContent) {
        this.quizContent = quizContent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", pass=" + pass + ", quizContent=" + quizContent + ", takeQuiz=" + takeQuiz + ", student=" + student + ", course=" + course + '}';
    }
 
}
