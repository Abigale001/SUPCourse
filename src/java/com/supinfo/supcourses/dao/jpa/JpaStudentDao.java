package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.StudentDao;
import com.supinfo.supcourses.entity.Student;
import com.supinfo.supcourses.entity.Student_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class JpaStudentDao implements StudentDao{

    @PersistenceContext(name = "SUPCoursesPU")
    private EntityManager em;
    
    //add new student
    @Override
    public Student addStudent(Student student) {
        em.persist(student);
        return student;
    }

    //find student by his/her username and password
    //used when the student logins to check if his/her username and password is correct
    @Override
    public Student findStudent(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> students = query.from(Student.class);
        List<Predicate> p = new ArrayList<>();
        p.add(cb.equal(students.get(Student_.username),username));
        p.add(cb.equal(students.get(Student_.password),password));
        query.where(p.toArray(new Predicate[p.size()]));
        try {
            return (Student) em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
        } 
        return null;
    }

    //find student by his/her username
    //used when the student registers to check if it already has this username
    @Override
    public Student findStudent(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> students = query.from(Student.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(students.get(Student_.username),username));
        query.where(p.toArray(new Predicate[p.size()]));
        try {
            return (Student) em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
        } 
        return null;
    }
    @Override
    public Student findStudentById(Long id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> students = query.from(Student.class);
        List<Predicate> p=new ArrayList<>();
        p.add(cb.equal(students.get(Student_.id),id));
        query.where(p.toArray(new Predicate[p.size()]));
        try {
            return (Student) em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
        } 
        return null;
    }
}
