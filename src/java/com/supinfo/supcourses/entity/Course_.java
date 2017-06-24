package com.supinfo.supcourses.entity;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Yicong
 */
@StaticMetamodel(Course.class)
public class Course_ {
    public static volatile SingularAttribute<Course, Long> id;
    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, Boolean> isView;
    public static volatile SingularAttribute<Course, String> description;
    public static volatile SingularAttribute<Course, StudentCourse> studentCourse;
    public static volatile SingularAttribute<Course, Date> fromDate;
    public static volatile SingularAttribute<Course, Date> endDate;
    public static volatile SingularAttribute<Course, String> moduleList;  
}
