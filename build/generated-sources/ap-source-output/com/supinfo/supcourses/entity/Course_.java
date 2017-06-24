package com.supinfo.supcourses.entity;

import com.supinfo.supcourses.entity.StudentCourse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-01T11:05:41")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, Date> fromDate;
    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, String> moduleList;
    public static volatile SingularAttribute<Course, Date> endDate;
    public static volatile SingularAttribute<Course, Boolean> isview;
    public static volatile SingularAttribute<Course, String> description;
    public static volatile SingularAttribute<Course, StudentCourse> studentCourse;
    public static volatile SingularAttribute<Course, Long> id;

}