package com.supinfo.supcourses.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Yicong
 */
@StaticMetamodel(Quiz.class)
public class Quiz_ {
    public static volatile SingularAttribute<Quiz, Long> id;
    public static volatile SingularAttribute<Quiz, String> quizContent;
    public static volatile SingularAttribute<Quiz, Boolean> pass;
    public static volatile SingularAttribute<Quiz, Boolean> takeQuiz;
    public static volatile SingularAttribute<Quiz, Student> student;
    public static volatile SingularAttribute<Quiz, Course> course;  
}
