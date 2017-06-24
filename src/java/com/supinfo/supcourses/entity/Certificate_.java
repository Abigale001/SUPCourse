package com.supinfo.supcourses.entity;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Yicong
 */
@StaticMetamodel(Certificate.class)
public class Certificate_ {
    public static volatile SingularAttribute<Certificate, Long> id;
    public static volatile SingularAttribute<Certificate, Quiz> quiz;
    public static volatile SingularAttribute<Certificate, Date> certificateDate;   
}
