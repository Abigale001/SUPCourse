package com.supinfo.supcourses.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String courseName;
    private String description;
    private boolean isview;
    
    @ManyToOne
    @JoinColumn(name = "STUDENTCOURSE_ID")
    StudentCourse studentCourse;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fromDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    private String moduleList;

    public Course() {
    }

    public Course(String courseName, String description, boolean isview, Date fromDate, Date endDate, String moduleList) {
        this.courseName = courseName;
        this.description = description;
        this.isview = isview;
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.moduleList = moduleList;
    }   

    public StudentCourse getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }
  
    public boolean isIsview() {
        return isview;
    }

    public void setIsview(boolean isview) {
        this.isview = isview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getModuleList() {
        return moduleList;
    }

    public void setModuleList(String moduleList) {
        this.moduleList = moduleList;
    }
    
    @Override
    public boolean equals(Object object){
        boolean isEqual = false;
        if(object != null && object instanceof Course){
            isEqual= (Objects.equals(this.id, ((Course) object).id));
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", courseName=" + courseName + ", description=" + description + ", isview=" + isview + ", studentCourse=" + studentCourse + ", fromDate=" + fromDate + ", endDate=" + endDate + ", moduleList=" + moduleList + '}';
    }
   
}
