package com.supinfo.supcourses.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author 五苦。
 */
@StaticMetamodel(StudentCourse.class)
public class StudentCourse_ {
    public static volatile SingularAttribute<StudentCourse,Long> id;
    public static volatile SingularAttribute<StudentCourse,Student> student;
    public static volatile SingularAttribute<StudentCourse,Course> course;         
}
