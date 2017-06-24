package com.supinfo.supcourses.entity;

import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-01T11:05:41")
@StaticMetamodel(Quiz.class)
public class Quiz_ { 

    public static volatile SingularAttribute<Quiz, String> quizContent;
    public static volatile SingularAttribute<Quiz, Boolean> pass;
    public static volatile SingularAttribute<Quiz, Student> student;
    public static volatile SingularAttribute<Quiz, Course> course;
    public static volatile SingularAttribute<Quiz, Long> id;
    public static volatile SingularAttribute<Quiz, Boolean> takeQuiz;

}