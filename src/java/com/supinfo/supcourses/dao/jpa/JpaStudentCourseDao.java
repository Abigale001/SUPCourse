package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.StudentCourseDao;
import com.supinfo.supcourses.entity.Course;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.entity.StudentCourse;
import com.supinfo.supcourses.entity.StudentCourse_;
import com.supinfo.supcourses.entity.Student_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Yicong
 */
@Stateless
public class JpaStudentCourseDao implements StudentCourseDao{

    @PersistenceContext
    private EntityManager em;

    @EJB
    private StudentCourseDao studentCourseDao;

    //add the student to StudentCourse
    //For now, the student has no courses.
    @Override
    public StudentCourse addStudent(Student student) {
        StudentCourse sc = new StudentCourse(student);
        em.persist(sc);
        return sc;
    }

    //add course to specific student
    //For now, the student has one or more courses.
    @Override
    public Course addCourseToStudent(Student student, Course course) {
        StudentCourse sc = studentCourseDao.findStudentCourseByStudentId(student.getId());
        try {
            List<Course> courses = sc.getCourses();
            courses.add(course);
            sc.setCourses(courses);
            System.out.println(courses);
            return em.merge(course);
        }catch(NullPointerException e){
            return em.merge(course);
        }
    }

    //find StudentCourse by studentId
    @Override
    public StudentCourse findStudentCourseByStudentId(Long id) {
        /*
        //First version using JPQL
        Query query = em.createQuery("SELECT sc FROM StudentCourse sc WHERE sc.student.id = :id");
        query.setParameter("id", id);
        try {
            return (StudentCourse) query.getSingleResult();
        }catch(NoResultException e){  
        }catch(Exception e){  
        }
        return null; 
        */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentCourse> query = cb.createQuery(StudentCourse.class);
        Root<StudentCourse> studentCourse = query.from(StudentCourse.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(studentCourse.get(StudentCourse_.student).get(Student_.id),id));
        query.where(p.toArray(new Predicate[p.size()]));
        try {
            return (StudentCourse) em.createQuery(query).getSingleResult();
        } catch (Exception e) {
        } 
        return null; 
    }

    //check if this student has taken this course
    @Override
    public Boolean isStudentHasThisCourse(Student student, Course course) {
       /*Query query = em.createQuery("SELECT sc FROM StudentCourse sc WHERE sc.student = :student");
        query.setParameter("student", student);
        StudentCourse sc = (StudentCourse) query.getSingleResult();
        for(Course item : sc.getCourses()) {
            if(item.equals(course)) {
                return true;
            }
        }
        return false;*/
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentCourse> query = cb.createQuery(StudentCourse.class);
        Root<StudentCourse> studentCourse = query.from(StudentCourse.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(studentCourse.get(StudentCourse_.student),student));
        query.where(p.toArray(new Predicate[p.size()]));
        StudentCourse sc=(StudentCourse) em.createQuery(query).getSingleResult();
        for(Course item : sc.getCourses()) {
            if(item.equals(course)) {
                return true;
            }
        }
        return false;
    }

    


    
    
}
