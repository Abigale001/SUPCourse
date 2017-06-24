package com.supinfo.supcourses.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Yicong
 */
@Entity
public class StudentCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Student student;
    
    @OneToMany(targetEntity = Course.class)
    List<Course> courses;
 
    public StudentCourse() {
    }

    public StudentCourse(Student student, List<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    public StudentCourse(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudentCourse{" + "id=" + id + ", student=" + student + ", courses=" + courses + '}';
    }
   
}
