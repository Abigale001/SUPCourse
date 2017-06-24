package com.supinfo.supcourses.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Yicong
 */
@Entity
public class Certificate implements Serializable {
    private static final long serialVersionUID = 1L;  
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "QUIZ_FK")
    private Quiz quiz;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date certificateDate;

    public Certificate() {
    }

    public Certificate(Quiz quiz, Date certificateDate) {
        this.quiz = quiz;
        this.certificateDate = certificateDate;
    }

    public Date getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(Date certificateDate) {
        this.certificateDate = certificateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
    @Override
    public String toString() {
        return "Certificate{" + "id=" + id + ", quiz=" + quiz + '}';
    }
    
    public String print(){
        return "Congratulations! " 
                + quiz.getStudent().getFirstname() 
                + " " 
                + quiz.getStudent().getLastname() 
                + " has passed the Course: " 
                + quiz.getCourse().getCourseName() 
                + ". Date: " 
                + this.getCertificateDate() 
                + ".";
    }
}
