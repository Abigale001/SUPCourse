package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.CoursesDao;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Course_;
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
public class JpaCoursesDao implements CoursesDao{

    @PersistenceContext
    private EntityManager em;

    //list all courses
    @Override
    public List<Course> listCourses() {
        return em.createQuery("SELECT c FROM Course c").getResultList();
    }

    //add new Course
    //just used in my populate servlet
    @Override
    public Course addCourse(Course course) {
        em.persist(course);
        return course;
    }

    //find course by courseId
    @Override
    public Course findCourseById(Long id) {
        /*
        //first version using JPQL
        Query query = em.createQuery("SELECT c FROM Course c WHERE c.id = :id");
        query.setParameter("id", id);
        try {
            return (Course) query.getSingleResult();
        }
        catch(NoResultException e) {  
        }
        return null;
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> Course = query.from(Course.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(Course.get(Course_.id),id));
        query.where(p.toArray(new Predicate[p.size()]));
        try {
            return (Course) em.createQuery(query).getSingleResult();
        } catch (Exception e) {
        } 
        return null;   
    }

    //find course by courseName
    @Override
    public Course findCourseByCourseName(String courseName) {
        /*
        //first version using JPQL
        Query query = em.createQuery("SELECT c FROM Course c WHERE c.courseName = :courseName");
        query.setParameter("courseName", courseName);
        try {
            return (Course) query.getSingleResult();
        }
        catch(NoResultException e) { 
        }
        return null;
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> Course = query.from(Course.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(Course.get(Course_.courseName),courseName));
        query.where(p.toArray(new Predicate[p.size()]));    
        try {
            return (Course) em.createQuery(query).getSingleResult();
        } catch (Exception e) {
        } 
        return null; 
    }   
}
