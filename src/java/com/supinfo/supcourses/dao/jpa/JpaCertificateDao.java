package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.CertificateDao;
import com.supinfo.supcourses.entity.Certificate;
import com.supinfo.supcourses.entity.Certificate_;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Quiz_;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.entity.Student_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Yicong
 */
@Stateless
public class JpaCertificateDao implements CertificateDao{

    @PersistenceContext
    private EntityManager em;
    
    //add certificate
    @Override
    public Certificate addCertificate(Certificate certificate) {
        em.persist(certificate);
        return certificate;
    }

    //list all certificates by studentId
    @Override
    public List<Certificate> listCertificatesByStudentId(Long id) {
        /*
        //first version using JPQL
        Query query = em.createQuery("SELECT c FROM Certificate c WHERE c.quiz.student.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Certificate> query = cb.createQuery(Certificate.class);
        Root<Certificate> C = query.from(Certificate.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(C.get(Certificate_.quiz).get(Quiz_.student).get(Student_.id),id));
        query.where(p.toArray(new Predicate[p.size()]));
        return  em.createQuery(query).getResultList();
    }

    //find certificate by certificateId
    @Override
    public Certificate findCertificateByCertificateId(Long id) {
        /*
        //first version using JPQL
        Query query = em.createQuery("SELECT c FROM Certificate c WHERE c.id = :id");
        query.setParameter("id", id);
        try{
            return (Certificate) query.getSingleResult();
        } catch(Exception e){    
        }
        return null;
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Certificate> query = cb.createQuery(Certificate.class);
        Root<Certificate> C = query.from(Certificate.class);
        List<Predicate> p = new ArrayList<>();
        p.add(cb.equal(C.get(Certificate_.id),id));       
        query.where(p.toArray(new Predicate[p.size()]));
        try {
            return  (Certificate)em.createQuery(query).getSingleResult();
        }catch(Exception e){            
        }
        return null;
    }  
}
