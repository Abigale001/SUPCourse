package com.supinfo.supcourses.entity;

import com.supinfo.supcourses.entity.Quiz;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-01T11:05:41")
@StaticMetamodel(Certificate.class)
public class Certificate_ { 

    public static volatile SingularAttribute<Certificate, Quiz> quiz;
    public static volatile SingularAttribute<Certificate, Date> certificateDate;
    public static volatile SingularAttribute<Certificate, Long> id;

}