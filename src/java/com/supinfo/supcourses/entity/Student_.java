package com.supinfo.supcourses.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Yicong
 */
@StaticMetamodel(Student.class)
public class Student_ {
    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, String> username;
    public static volatile SingularAttribute<Student, String> password;
    public static volatile SingularAttribute<Student, String> firstname;
    public static volatile SingularAttribute<Student, String> lastname;
}
