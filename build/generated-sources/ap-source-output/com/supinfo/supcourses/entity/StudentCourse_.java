package com.supinfo.supcourses.entity;

import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-01T11:05:41")
@StaticMetamodel(StudentCourse.class)
public class StudentCourse_ { 

    public static volatile ListAttribute<StudentCourse, Course> courses;
    public static volatile SingularAttribute<StudentCourse, Student> student;
    public static volatile SingularAttribute<StudentCourse, Long> id;

}